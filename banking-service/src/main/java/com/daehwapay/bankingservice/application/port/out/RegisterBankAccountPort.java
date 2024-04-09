package com.daehwapay.bankingservice.application.port.out;

import com.daehwapay.bankingservice.adapter.out.persistence.RegisteredBankAccountEntity;

public interface RegisterBankAccountPort {
    RegisteredBankAccountEntity register(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusValid);
}
