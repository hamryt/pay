package com.daehwapay.paymentservice.domain;

import com.daehwapay.paymentservice.adapter.out.persistence.PaymentRequestEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Payment {
    private Long paymentId;
    private Long requestMembershipId;
    private int requestPrice;
    private int franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;
    private Date approvedAt;

    public static Payment from(PaymentRequestEntity entity) {
        return Payment.builder()
                .paymentId(entity.getPaymentId())
                .requestMembershipId(entity.getRequestMembershipId())
                .requestPrice(entity.getRequestPrice())
                .franchiseId(entity.getFranchiseId())
                .franchiseFeeRate(entity.getFranchiseFeeRate())
                .paymentStatus(entity.getPaymentStatus())
                .approvedAt(entity.getApprovedAt())
                .build();
    }
}
