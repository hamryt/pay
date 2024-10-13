package com.daehwapay.moneyaggregationservice.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FindMemberMoneyRequest {
    private List<Long> membershipIds;
}
