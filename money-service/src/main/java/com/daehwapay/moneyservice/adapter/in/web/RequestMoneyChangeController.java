package com.daehwapay.moneyservice.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.moneyservice.application.port.in.CreateMemberMoneyCommand;
import com.daehwapay.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.daehwapay.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
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

    private final IncreaseMoneyRequestUseCase useCase;

    @PostMapping(path = "/money/increase")
    ResponseEntity<MoneyChangeResultDetail> increaseMoney(@RequestBody IncreaseMoneyChangeRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .corporation(request.isCorporation())
                .build();

        MoneyChangingRequest moneyChangingRequest = useCase.increaseMoney(command);

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

        MoneyChangingRequest moneyChangingRequest = useCase.increaseMoneyAsync(command);

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
        useCase.createMemberMoney(new CreateMemberMoneyCommand(request.getTargetMembershipId()));
    }
}
