package com.daehwapay.paymentservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePaymentRequestCommand {
    private Long requestMembershipId;
    private Integer requestPrice;
    private Integer franchiseId;
    private String franchiseFeeRate;
}
