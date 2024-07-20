package com.daehwapay.moneyservice.adapter.in.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMemberMoneyEvent {
    private String id;
    private Long membershipId;
    private int balance;
}
