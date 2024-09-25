package com.daehwapay.common.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredBankAccountCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private int amount;
}
