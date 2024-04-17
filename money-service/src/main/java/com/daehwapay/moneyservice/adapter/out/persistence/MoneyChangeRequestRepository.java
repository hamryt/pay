package com.daehwapay.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyChangeRequestRepository extends JpaRepository<MoneyChangeRequestEntity, Long> {
}
