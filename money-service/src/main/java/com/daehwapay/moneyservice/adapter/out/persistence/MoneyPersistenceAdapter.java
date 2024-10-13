package com.daehwapay.moneyservice.adapter.out.persistence;

import com.daehwapay.common.PersistenceAdapter;
import com.daehwapay.moneyservice.application.port.out.CreateMoneyPort;
import com.daehwapay.moneyservice.application.port.out.GetMoneyPort;
import com.daehwapay.moneyservice.application.port.out.IncreaseMoneyPort;
import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyPersistenceAdapter implements IncreaseMoneyPort, CreateMoneyPort, GetMoneyPort {

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
            return memberMoneyRepository.save(new MemberMoneyEntity(membershipId, amount, null));
        }

        int balance = memberMoneyEntity.getBalance();

        memberMoneyEntity.setBalance(balance + amount);
        return memberMoneyRepository.save(memberMoneyEntity);
    }

    @Override
    public MemberMoneyEntity save(Long membershipId, int balance, String aggregateIdentifier) {
        MemberMoneyEntity entity = new MemberMoneyEntity(membershipId, balance, aggregateIdentifier);

        return memberMoneyRepository.save(entity);
    }

    @Override
    public MemberMoneyEntity getMemberMoneyById(Long id) {
        return memberMoneyRepository.findFirstByMembershipId(id);
    }

    @Override
    public List<MemberMoneyEntity> getMemberMoneyByIdIn(List<Long> membershipIds) {
        return memberMoneyRepository.findByMembershipIdIn(membershipIds);
    }
}
