package com.daehwapay.moneyaggregationservice.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoney {
    private String memberMoneyId;
    private String membershipId;
    private int balance;
}