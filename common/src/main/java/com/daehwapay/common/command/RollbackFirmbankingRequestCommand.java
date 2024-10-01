package com.daehwapay.common.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Builder
public class RollbackFirmbankingRequestCommand {
    private String rollbackFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private int moneyAmount;
}
