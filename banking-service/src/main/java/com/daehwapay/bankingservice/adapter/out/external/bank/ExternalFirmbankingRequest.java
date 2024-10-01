package com.daehwapay.bankingservice.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExternalFirmbankingRequest {
    private String fromBankName;
    private String fromBankAccount;
    private String toBankName;
    private String toBankAccount;
    private int amount;
}
