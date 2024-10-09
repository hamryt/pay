package com.daehwapay.paymentservice.adapter.in.web;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequest {
    private Long requestMembershipId;
    private Integer requestPrice;
    private Integer franchiseId;
    private String franchiseFeeRate;
}
