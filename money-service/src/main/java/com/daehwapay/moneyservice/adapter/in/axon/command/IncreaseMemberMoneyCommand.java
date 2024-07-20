package com.daehwapay.moneyservice.adapter.in.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncreaseMemberMoneyCommand {
    @NotNull
    @TargetAggregateIdentifier
    private String id;
    private Long membershipId;
    private int balance;
}
