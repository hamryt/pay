package com.daehwapay.bankingservice.application.port.out;

import com.daehwapay.bankingservice.adapter.out.external.bank.BankAccount;
import com.daehwapay.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.daehwapay.bankingservice.adapter.out.persistence.RegisteredBankAccountEntity;

public interface RegisterBankAccountPort {
    RegisteredBankAccountEntity register(String membershipId, String bankName, String bankAccountNumber, String aggregateIdentifier, boolean linkedStatusValid);

    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
