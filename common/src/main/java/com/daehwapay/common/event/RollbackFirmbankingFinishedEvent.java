package com.daehwapay.common.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RollbackFirmbankingFinishedEvent {
    private String rollbackFirmbankingId;
    private String membershipId;
    private String rollbackFirmbankingAggregateIdentifier;

}
