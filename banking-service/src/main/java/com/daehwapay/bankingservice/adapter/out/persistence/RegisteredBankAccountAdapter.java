package com.daehwapay.bankingservice.adapter.out.persistence;

import com.daehwapay.bankingservice.application.port.out.RegisterBankAccountPort;
import com.daehwapay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountAdapter implements RegisterBankAccountPort {
    private final RegisteredBankAccountRepository repository;

    @Override
    public RegisteredBankAccountEntity register(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusValid) {
        return repository.save(new RegisteredBankAccountEntity(membershipId, bankName, bankAccountNumber, linkedStatusValid));
    }
}
