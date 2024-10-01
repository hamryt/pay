package com.daehwapay.bankingservice.adapter.in.web;

import com.daehwapay.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.daehwapay.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.daehwapay.bankingservice.domain.RegisteredBankAccount;
import com.daehwapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankUseCase;

    @PostMapping(path = "/banking/accounts")
    ResponseEntity<RegisteredBankAccount> registerBankAccount(
            @RequestBody RegisterBankAccountRequest request
    ) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusValid(request.isLinkedStatusValid())
                .build();

        return ResponseEntity.ok(registerBankUseCase.registerBankAccount(command));
    }

    @PostMapping(path = "/banking/account/register-eda")
    void registeredBankAccountByEvent(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusValid(request.isLinkedStatusValid())
                .build();

        registerBankUseCase.registerBankAccountByEvent(command);
    }
}
