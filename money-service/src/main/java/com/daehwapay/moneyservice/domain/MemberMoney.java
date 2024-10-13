package com.daehwapay.moneyservice.domain;

import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberMoney {
    private final Long id;
    private final Long membershipId;
    private final int balance;

    public static MemberMoney from(MemberMoneyEntity entity) {
        return MemberMoney.builder()
                .id(entity.getId())
                .membershipId(entity.getMembershipId())
                .balance(entity.getBalance())
                .build();
    }
}
