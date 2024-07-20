package com.daehwapay.moneyservice.adapter.in.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncreaseMemberMoneyRequest {
    private long membershipId;
    private int balance;
    private boolean corporation;
}
