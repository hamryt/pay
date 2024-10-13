package com.daehwapay.moneyaggregationservice.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoney {
    private Long id;
    private Long membershipId;
    private int balance;
}
