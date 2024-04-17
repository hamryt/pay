package com.daehwapay.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyEntity, Long> {
    MemberMoneyEntity findFirstByMembershipId(Long membershipId);
}
