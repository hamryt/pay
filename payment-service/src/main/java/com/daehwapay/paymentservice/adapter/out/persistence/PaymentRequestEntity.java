package com.daehwapay.paymentservice.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "payment_request")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestEntity {
    @Id
    @GeneratedValue
    private Long paymentId;
    private Long requestMembershipId;
    private int requestPrice;
    private int franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;  // 0: 승인, 1: 실패, 2: 정산 완료.
    private Date approvedAt;

    public PaymentRequestEntity(Long requestMembershipId, int requestPrice, int franchiseId, String franchiseFeeRate, int paymentStatus, Date approvedAt) {
        this.requestMembershipId = requestMembershipId;
        this.requestPrice = requestPrice;
        this.franchiseId = franchiseId;
        this.franchiseFeeRate = franchiseFeeRate;
        this.paymentStatus = paymentStatus;
        this.approvedAt = approvedAt;
    }
}
