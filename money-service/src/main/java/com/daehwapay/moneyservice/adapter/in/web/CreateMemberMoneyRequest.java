package com.daehwapay.moneyservice.adapter.in.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberMoneyRequest {
    private String targetMembershipId;

    CreateMemberMoneyRequest(String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;
    }
}
