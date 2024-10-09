package com.daehwapay.paymentservice.application.service;

import com.daehwapay.common.UseCase;
import com.daehwapay.paymentservice.application.port.in.CreatePaymentRequestCommand;
import com.daehwapay.paymentservice.application.port.in.CreatePaymentRequestUseCase;
import com.daehwapay.paymentservice.application.port.out.CreatePaymentRequestPort;
import com.daehwapay.paymentservice.domain.Payment;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PaymentService implements CreatePaymentRequestUseCase {
    private final CreatePaymentRequestPort createPaymentPort;

    @Override
    public Payment createPaymentRequest(CreatePaymentRequestCommand command) {
        // 충전도, 멤버십, 머니 유효성 확인.....
        // getMembershipPort.getMembership(command.getRequestMembershipId());

        //getRegisteredBankAccountPort.getRegisteredBankAccount(command.getRequestMembershipId());

        return createPaymentPort.create(
                command.getRequestMembershipId(),
                command.getRequestPrice(),
                command.getFranchiseId(),
                command.getFranchiseFeeRate());
    }
}
