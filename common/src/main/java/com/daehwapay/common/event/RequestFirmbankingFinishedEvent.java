package com.daehwapay.common.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestFirmbankingFinishedEvent {
    private String requestFirmbankingId;
    private String rechargingRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int amount;
    private int status;
    private String requestFirmbankingAggregateIdentifier;
}
