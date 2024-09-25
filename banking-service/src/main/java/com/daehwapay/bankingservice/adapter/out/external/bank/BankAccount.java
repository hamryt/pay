package com.daehwapay.bankingservice.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BankAccount {
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean valid;
}
