package com.daehwapay.common.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Builder
public class RequestFirmbankingCommand {
    private String requestFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargingRequestId;
    private String membershipId;

    private String fromBankName;
    private String fromBankAccountNumber;

    private String toBankName;
    private String toBankAccountNumber;

    private int amount;
}
