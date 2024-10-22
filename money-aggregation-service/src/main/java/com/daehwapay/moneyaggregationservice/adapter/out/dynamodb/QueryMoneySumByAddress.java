package com.daehwapay.moneyaggregationservice.adapter.out.dynamodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QueryMoneySumByAddress {
    private String address;
}
