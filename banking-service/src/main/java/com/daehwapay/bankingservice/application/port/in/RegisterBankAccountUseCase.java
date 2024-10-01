package com.daehwapay.bankingservice.application.port.in;

import com.daehwapay.bankingservice.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
    void registerBankAccountByEvent(RegisterBankAccountCommand command);
}
