package com.daehwapay.bankingservice.adapter.out.external.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBankAccountRequest {
    private String bankName;
    private String bankAccountNumber;

    public GetBankAccountRequest(String bankName, String bankAccountNumber) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
    }
}
