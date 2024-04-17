package com.daehwapay.bankingservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    private final Long registeredBankAccountId;
    private final String membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean linkedStatusValid;

    public static RegisteredBankAccount generate(Long registeredBankAccountId,
                                                 String membershipId,
                                                 String bankName,
                                                 String bankAccountNumber,
                                                 boolean linkedStatusValid) {
        return RegisteredBankAccount.builder()
                .registeredBankAccountId(registeredBankAccountId)
                .membershipId(membershipId)
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .linkedStatusValid(linkedStatusValid)
                .build();

    }
}
