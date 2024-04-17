package com.daehwapay.moneyservice.application.port.out;

import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;
import com.daehwapay.moneyservice.adapter.out.persistence.MoneyChangeRequestEntity;
import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;

import java.util.UUID;

public interface IncreaseMoneyPort {
    MoneyChangeRequestEntity createMoneyChange(
            Long targetMembershipId,
            ChangingType moneyChangingType,
            int moneyAmount,
            boolean corporation,
            ChangingMoneyStatus changingMoneyStatus,
            UUID uuid
    );

    MemberMoneyEntity increaseMoney(Long membershipId, int amount);
}
