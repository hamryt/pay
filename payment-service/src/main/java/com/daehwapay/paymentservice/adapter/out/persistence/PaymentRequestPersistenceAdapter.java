package com.daehwapay.paymentservice.adapter.out.persistence;

import com.daehwapay.common.PersistenceAdapter;
import com.daehwapay.paymentservice.application.port.out.CreatePaymentRequestPort;
import com.daehwapay.paymentservice.domain.Payment;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PaymentRequestPersistenceAdapter implements CreatePaymentRequestPort {
    private final PaymentRequestRepository repository;

    @Override
    public Payment create(Long requestMembershipId, Integer requestPrice, Integer franchiseId, String franchiseFeeRate) {
        PaymentRequestEntity entity = repository.save(new PaymentRequestEntity(
                requestMembershipId,
                requestPrice,
                franchiseId,
                franchiseFeeRate,
                0,
                null
        ));

        return Payment.from(entity);
    }
}
