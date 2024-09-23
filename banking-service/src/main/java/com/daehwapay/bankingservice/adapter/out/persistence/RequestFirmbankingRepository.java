package com.daehwapay.bankingservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestFirmbankingRepository extends JpaRepository<RequestFirmbankingEntity, Long> {
    RequestFirmbankingEntity findFirstByAggregateIdentifier(String aggregateIdentifier);
}
