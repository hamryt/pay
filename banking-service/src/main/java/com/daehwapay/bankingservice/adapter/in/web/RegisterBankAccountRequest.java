package com.daehwapay.bankingservice.adapter.in.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBankAccountRequest {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private boolean linkedStatusValid;
}
