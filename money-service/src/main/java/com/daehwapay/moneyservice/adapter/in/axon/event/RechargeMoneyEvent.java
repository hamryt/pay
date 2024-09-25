package com.daehwapay.moneyservice.adapter.in.axon.event;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RechargeMoneyEvent {
    private String rechargingRequestId;
    private String membershipId;
    private int amount;

    private String bankingAccountAggregateIdentifier;
    private String bankName;
    private String bankAccountNumber;
}
