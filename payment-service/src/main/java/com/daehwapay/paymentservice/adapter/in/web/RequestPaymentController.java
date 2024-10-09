package com.daehwapay.paymentservice.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.paymentservice.application.port.in.CreatePaymentRequestCommand;
import com.daehwapay.paymentservice.application.port.in.CreatePaymentRequestUseCase;
import com.daehwapay.paymentservice.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {

    private final CreatePaymentRequestUseCase usecase;

    @PostMapping(path = "/payment/request")
    Payment requestPayment(PaymentRequest request) {

        CreatePaymentRequestCommand command = CreatePaymentRequestCommand.builder()
                .requestMembershipId(request.getRequestMembershipId())
                .requestPrice(request.getRequestPrice())
                .franchiseId(request.getFranchiseId())
                .franchiseFeeRate(request.getFranchiseFeeRate())
                .build();

        return usecase.createPaymentRequest(command);
    }
}
