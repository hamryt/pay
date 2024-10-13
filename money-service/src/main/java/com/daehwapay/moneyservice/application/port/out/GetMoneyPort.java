package com.daehwapay.moneyservice.application.port.out;

import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;

import java.util.List;

public interface GetMoneyPort {
    MemberMoneyEntity getMemberMoneyById(Long id);

    List<MemberMoneyEntity> getMemberMoneyByIdIn(List<Long> membershipIds);
}
