package com.daehwapay.moneyservice.adapter.in.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IncreaseMoneyRequest {
    private Long targetMembershipId;
    private int amount;
}
