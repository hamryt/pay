package com.daehwapay.moneyaggregationservice.adapter.out.dynamodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneySumByAddress {
    private String pk;
    private String sk;
    private int balance;
}
