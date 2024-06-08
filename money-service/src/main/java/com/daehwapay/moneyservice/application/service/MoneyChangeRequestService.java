package com.daehwapay.moneyservice.application.service;

import com.daehwapay.common.CountDownLatchManager;
import com.daehwapay.common.RechargingMoneyTask;
import com.daehwapay.common.SubTask;
import com.daehwapay.common.UseCase;
import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;
import com.daehwapay.moneyservice.adapter.out.persistence.MoneyChangeRequestEntity;
import com.daehwapay.moneyservice.adapter.out.persistence.MoneyChangeRequestMapper;
import com.daehwapay.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.daehwapay.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.daehwapay.moneyservice.application.port.out.IncreaseMoneyPort;
import com.daehwapay.moneyservice.application.port.out.SendRechargingMoneyTaskPort;
import com.daehwapay.moneyservice.domain.MoneyChangingRequest;
import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class MoneyChangeRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final CountDownLatchManager countDownLatchManager;
    private final MoneyChangeRequestMapper moneyChangeRequestMapper;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;

    @Override
    public MoneyChangingRequest increaseMoney(IncreaseMoneyRequestCommand command) {
        /**
         * 머니 충전 과정
         * 1. 고객 정보가 정상인지 확인 (멤버)
         * 2. 고객 계좌가 존재하는지 잔액이 충분한지 확인 (머니)
         * 3. 법인의 계좌가 정상인지 확인 (뱅킹)
         * 4. 펌뱅킹을 요청 (뱅킹)
         * 5-1. 고객 머니 증액 성공 (머니)
         *      1. 고객 머니 증액 요청 (머니)
         * 5-2. 고객 머니 증액 실패
         * 6. MoneyChangeRequest 저장 (머니)
         */

        // 5-1 1. 고객 머니 증액 요청
        MemberMoneyEntity memberMoneyEntity = increaseMoneyPort.increaseMoney(command.getTargetMembershipId(), command.getAmount());

        // 6. MoneyChangeRequest 저장
        if (memberMoneyEntity != null) {
            MoneyChangeRequestEntity moneyChangeRequest = increaseMoneyPort.createMoneyChange(
                    command.getTargetMembershipId(),
                    ChangingType.INCREASE,
                    command.getAmount(),
                    command.isCorporation(),
                    ChangingMoneyStatus.SUCCEEDED,
                    UUID.randomUUID()
            );

            return moneyChangeRequestMapper.entityToDomain(moneyChangeRequest);
        }

        return null;
    }

    @Override
    public MoneyChangingRequest increaseMoneyAsync(IncreaseMoneyRequestCommand command) {

        // 1. subtask, Task
        SubTask validMemberTask = SubTask.builder()
                .subTaskName("validMemberTask" + "멤버십 유효성 검사")
                .membershipId(command.getTargetMembershipId())
                .taskType("membership")
                .status("ready")
                .build();

        // Banking Sub task
        // Banking Account Validation --> 무조건 ok 받았다고 가정.
        SubTask validBankingAccountTask = SubTask.builder()
                .subTaskName("validBankingAccountTask" + "뱅킹 계좌 유효성 검사")
                .membershipId(command.getTargetMembershipId())
                .taskType("membership")
                .status("ready")
                .build();

        List<SubTask> subTasks = new ArrayList<>();
        subTasks.add(validMemberTask);
        subTasks.add(validBankingAccountTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskId(UUID.randomUUID().toString())
                .taskName("Increase Money Task / 머니 충전 Task")
                .subTasks(subTasks)
                .moneyAmount(command.getAmount())
                .membershipId(command.getTargetMembershipId())
                .toBankName("kakao 가고 싶다")
                .build();

        // 2. kafka Cluster Produce
        // Task Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);

        countDownLatchManager.addCountDownLatch(task.getTaskId());

        // 3. wait
        try {
            countDownLatchManager.getCountDownLatch(task.getTaskId()).await();
            String result = countDownLatchManager.getDataForKey(task.getTaskId());
            if (result.equals("success")) {
                System.out.println("success for async Money Recharging!!");
                return getMoneyChangingRequest(command);
            } else {
                return null;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 3-1. task-consumer
        //  등록된 sub-task, status 모두 ok -> task 결과를 produce
    }

    @Nullable
    private MoneyChangingRequest getMoneyChangingRequest(IncreaseMoneyRequestCommand command) {
        MemberMoneyEntity memberMoneyEntity = increaseMoneyPort.increaseMoney(
                command.getTargetMembershipId(), command.getAmount()
        );

        if (memberMoneyEntity != null) {
            return moneyChangeRequestMapper.entityToDomain(increaseMoneyPort.createMoneyChange(
                    command.getTargetMembershipId(),
                    ChangingType.INCREASE,
                    command.getAmount(),
                    command.isCorporation(),
                    ChangingMoneyStatus.SUCCEEDED,
                    UUID.randomUUID()
            ));
        }

        return null;
    }
}
