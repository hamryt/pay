package com.daehwapay.bankingservice.adapter.out.persistence;

import com.daehwapay.bankingservice.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount entityToDomain(RegisteredBankAccountEntity entity) {
        return RegisteredBankAccount.generate(
                entity.getRegisteredBankAccountId(),
                entity.getMembershipId(),
                entity.getBankName(),
                entity.getBankAccountNumber(),
                entity.isLinkedStatusValid()
        );
    }
}
