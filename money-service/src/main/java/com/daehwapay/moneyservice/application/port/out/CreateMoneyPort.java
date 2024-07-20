package com.daehwapay.moneyservice.application.port.out;

import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;

public interface CreateMoneyPort {
    MemberMoneyEntity save(Long membershipId, int balance, String aggregateIdentifier);
}
