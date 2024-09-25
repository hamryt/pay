package com.daehwapay.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckedRegisteredBankAccountEvent {
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private boolean isChecked;

    private int amount;

    private String firmbankingRequestAggregateIdentifier;

    private String toBankName;
    private String toBankAccountNumber;
}
