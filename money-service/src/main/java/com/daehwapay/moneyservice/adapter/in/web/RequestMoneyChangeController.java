package com.daehwapay.moneyservice.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.moneyservice.application.port.in.*;
import com.daehwapay.moneyservice.domain.MoneyChangingRequest;
import com.daehwapay.moneyservice.enums.MoneyChangingResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangeController {

    private final IncreaseMoneyRequestUseCase increaseUseCase;
    private final CreateMemberMoneyUseCase createUseCase;

    @PostMapping(path = "/money/increase")
    ResponseEntity<MoneyChangeResultDetail> increaseMoney(@RequestBody IncreaseMoneyChangeRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .corporation(request.isCorporation())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseUseCase.increaseMoney(command);

        MoneyChangeResultDetail detail = MoneyChangeResultDetail.builder()
                .moneyChangingRequestId(moneyChangingRequest.getMoneyChangingRequestId())
                .moneyChangeType(moneyChangingRequest.getMoneyChangeType())
                .moneyChangingType(MoneyChangingResultStatus.SUCCEEDED)
                .amount(moneyChangingRequest.getMoneyAmount())
                .build();

        return ResponseEntity.ok(detail);
    }

    @PostMapping(path = "/money/increase/async")
    ResponseEntity<MoneyChangeResultDetail> increaseMoneyAsync(@RequestBody IncreaseMoneyChangeRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .corporation(request.isCorporation())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseUseCase.increaseMoneyAsync(command);

        MoneyChangeResultDetail detail = MoneyChangeResultDetail.builder()
                .moneyChangingRequestId(moneyChangingRequest.getMoneyChangingRequestId())
                .moneyChangeType(moneyChangingRequest.getMoneyChangeType())
                .moneyChangingType(MoneyChangingResultStatus.SUCCEEDED)
                .amount(moneyChangingRequest.getMoneyAmount())
                .build();

        return ResponseEntity.ok(detail);
    }

    @PostMapping(path = "/money/create-member-money")
    void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {
        createUseCase.createMemberMoney(new CreateMemberMoneyCommand(request.getTargetMembershipId()));
    }

    @PostMapping(path = "/money/increase-eda")
    void increaseMoneyChangingRequestByEvent(@RequestBody IncreaseMoneyRequest request) {
        IncreaseMoneyCommand command = IncreaseMoneyCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        increaseUseCase.increaseMoneyByEvent(command);
    }

    @PostMapping(path = "/money/increate-member-money")
    void increaseMemberMoney(@RequestBody IncreaseMemberMoneyRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getMembershipId())
                .amount(request.getBalance())
                .corporation(request.isCorporation())
                .build();

        increaseUseCase.increaseMemberMoney(command);
    }
}
