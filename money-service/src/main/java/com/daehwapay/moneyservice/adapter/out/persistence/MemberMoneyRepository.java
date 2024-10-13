package com.daehwapay.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyEntity, Long> {
    MemberMoneyEntity findFirstByMembershipId(Long membershipId);
    List<MemberMoneyEntity> findByMembershipIdIn(List<Long> membershipIds);
}
