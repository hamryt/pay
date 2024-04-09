package com.daehwapay.bankingservice.adapter.out.external.bank;

import com.daehwapay.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.daehwapay.common.ExternalAdapter;

@ExternalAdapter
public class BankAccountAdapter implements RequestBankAccountInfoPort {
    @Override
    public BankAccount requestBankAccount(GetBankAccountRequest request) {
        return new BankAccount(request.getBankName(), request.getBankName());
    }
}
