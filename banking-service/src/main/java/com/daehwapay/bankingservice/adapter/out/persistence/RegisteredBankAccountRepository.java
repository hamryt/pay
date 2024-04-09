package com.daehwapay.bankingservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountEntity, Long> {
}
