package com.daehwapay.paymentservice.application.port.in;

import com.daehwapay.paymentservice.domain.Payment;

public interface CreatePaymentRequestUseCase {
    Payment createPaymentRequest(CreatePaymentRequestCommand command);
}
