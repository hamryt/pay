package com.daehwapay.bankingservice.application.port.out;

import com.daehwapay.bankingservice.adapter.out.external.bank.BankAccount;
import com.daehwapay.bankingservice.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount requestBankAccount(GetBankAccountRequest request);
}
