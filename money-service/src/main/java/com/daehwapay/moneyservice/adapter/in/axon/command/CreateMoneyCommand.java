package com.daehwapay.moneyservice.adapter.in.axon.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMoneyCommand {
    private String membershipId;

    public CreateMoneyCommand(String membershipId) {
        this.membershipId = membershipId;
    }
}
