package com.daehwapay.paymentservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRequestRepository extends JpaRepository<PaymentRequestEntity, Long> {
}
