package com.daehwapay.bankingservice.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegisteredBankAccountCommand {
    private String membershipId;

    private String bankName;
    private String bankAccountNumber;
}