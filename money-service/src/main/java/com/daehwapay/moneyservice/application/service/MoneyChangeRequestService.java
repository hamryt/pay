package com.daehwapay.moneyservice.application.service;

import com.daehwapay.common.UseCase;
import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;
import com.daehwapay.moneyservice.adapter.out.persistence.MoneyChangeRequestEntity;
import com.daehwapay.moneyservice.adapter.out.persistence.MoneyChangeRequestMapper;
import com.daehwapay.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.daehwapay.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.daehwapay.moneyservice.application.port.out.IncreaseMoneyPort;
import com.daehwapay.moneyservice.domain.MoneyChangingRequest;
import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class MoneyChangeRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangeRequestMapper moneyChangeRequestMapper;

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
}
