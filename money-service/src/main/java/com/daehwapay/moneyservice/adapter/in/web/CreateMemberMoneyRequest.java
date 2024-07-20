package com.daehwapay.moneyservice.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMemberMoneyRequest {
    private String targetMembershipId;

    CreateMemberMoneyRequest(String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;
    }
}
