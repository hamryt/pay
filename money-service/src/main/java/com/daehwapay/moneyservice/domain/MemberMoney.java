package com.daehwapay.moneyservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberMoney {
    private final String id;
    private final Long membershipId;
    private final int balance;
}
