package com.daehwapay.moneyservice.adapter.out.persistence;

import com.daehwapay.common.PersistenceAdapter;
import com.daehwapay.moneyservice.application.port.out.IncreaseMoneyPort;
import com.daehwapay.moneyservice.domain.MemberMoney;
import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangeRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final MoneyChangeRequestRepository moneyChangeRequestRepository;
    private final MemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangeRequestEntity createMoneyChange(
            Long targetMembershipId,
            ChangingType moneyChangingType,
            int moneyAmount,
            boolean corporation,
            ChangingMoneyStatus changingMoneyStatus,
            UUID uuid
    ) {
        return moneyChangeRequestRepository.save(
                new MoneyChangeRequestEntity(
                        targetMembershipId,
                        moneyChangingType,
                        moneyAmount,
                        new Timestamp(System.currentTimeMillis()),
                        corporation,
                        changingMoneyStatus,
                        uuid
                )
        );
    }

    @Override
    @Transactional
    public MemberMoneyEntity increaseMoney(Long membershipId, int amount) {
        MemberMoneyEntity memberMoneyEntity = memberMoneyRepository.findFirstByMembershipId(membershipId);

        if (memberMoneyEntity == null) {
            return memberMoneyRepository.save(new MemberMoneyEntity(membershipId, amount));
        }

        int balance = memberMoneyEntity.getBalance();

        memberMoneyEntity.setBalance(balance + amount);
        return memberMoneyRepository.save(memberMoneyEntity);
    }
}
