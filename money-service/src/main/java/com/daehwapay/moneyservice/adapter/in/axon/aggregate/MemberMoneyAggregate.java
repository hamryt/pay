package com.daehwapay.moneyservice.adapter.in.axon.aggregate;

import com.daehwapay.moneyservice.adapter.in.axon.command.CreateMoneyCommand;
import com.daehwapay.moneyservice.adapter.in.axon.event.CreateMemberMoneyEvent;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Table(name = "member_money")
@Aggregate()
@Data
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    @CommandHandler
    public String MemberMoneyAggregate(@NotNull CreateMoneyCommand command) {
        System.out.println("Create money command Handler");

        apply(new CreateMemberMoneyEvent(command.getMembershipId()));

        return id;
    }
}
