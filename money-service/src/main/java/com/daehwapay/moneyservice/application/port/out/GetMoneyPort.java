package com.daehwapay.moneyservice.application.port.out;

import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;

public interface GetMoneyPort {
    MemberMoneyEntity getMemberMoneyById(Long id);
}
