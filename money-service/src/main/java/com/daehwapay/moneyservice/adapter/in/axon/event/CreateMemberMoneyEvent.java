package com.daehwapay.moneyservice.adapter.in.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateMemberMoneyEvent {
    private String membershipId;
}
