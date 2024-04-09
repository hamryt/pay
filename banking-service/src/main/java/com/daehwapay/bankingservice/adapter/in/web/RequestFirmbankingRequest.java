package com.daehwapay.bankingservice.adapter.in.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFirmbankingRequest {
    private String fromBankName;
    private String fromBankAccount;
    private String toBankName;
    private String toBankAccount;
    private int moneyAmount;
}
