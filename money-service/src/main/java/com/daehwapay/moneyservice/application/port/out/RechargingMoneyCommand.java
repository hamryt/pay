package com.daehwapay.moneyservice.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargingMoneyCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargingRequestId;
    private String membershipId;
    private int amount;
}
