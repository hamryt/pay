package com.daehwapay.moneyservice.adapter.in.axon.event;

import lombok.Data;

@Data
public class CreateMemberMoneyEvent {
    private String membershipId;

    public CreateMemberMoneyEvent(String membershipId) {
        this.membershipId = membershipId;
    }
}
