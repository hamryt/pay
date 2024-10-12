package com.daehwapay.moneyservice.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateMoneyRequest {
    private Long targetMembershipId;
    private int amount;
}
