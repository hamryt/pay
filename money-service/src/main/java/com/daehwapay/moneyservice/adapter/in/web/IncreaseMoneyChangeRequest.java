package com.daehwapay.moneyservice.adapter.in.web;

import lombok.*;

@Getter
@Setter
public class IncreaseMoneyChangeRequest {
    private Long targetMembershipId;
    private int amount;
    private boolean valid;
    private boolean corporation;
}
