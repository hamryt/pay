package com.daehwapay.paymentservice.application.port.out;

import com.daehwapay.paymentservice.domain.Payment;

public interface CreatePaymentRequestPort {
    Payment create(Long requestMembershipId, Integer requestPrice, Integer franchiseId, String franchiseFeeRate);
}
