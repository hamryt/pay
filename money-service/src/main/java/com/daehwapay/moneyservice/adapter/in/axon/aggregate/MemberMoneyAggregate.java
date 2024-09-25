package com.daehwapay.moneyservice.adapter.in.axon.aggregate;

import com.daehwapay.moneyservice.adapter.in.axon.command.CreateMoneyCommand;
import com.daehwapay.moneyservice.adapter.in.axon.event.CreateMemberMoneyEvent;
import com.daehwapay.moneyservice.adapter.in.axon.command.IncreaseMemberMoneyCommand;
import com.daehwapay.moneyservice.adapter.in.axon.event.IncreaseMemberMoneyEvent;
import com.daehwapay.moneyservice.adapter.in.axon.event.RechargeMoneyEvent;
import com.daehwapay.moneyservice.application.port.out.RechargingMoneyCommand;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Table(name = "member_money")
@Aggregate()
@Data
@NoArgsConstructor
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(@NotNull CreateMoneyCommand command) {
        System.out.println("Create money command Handler");

        apply(new CreateMemberMoneyEvent(command.getMembershipId()));
    }

    @CommandHandler
    public void handle(RechargingMoneyCommand command) {
        System.out.println("RechargingMoneyCommand handler");
        id = command.getAggregateIdentifier();
        System.out.println("RechargingMoneyCommand handler command aggregate identifier: " + id);

//        원래는 S to S 로 뱅킹 정보를 가져와야 하지만 mocking으로 처리
//        RegisteredBankAccountAggregateIdentifier bankAccountAggregate = getRegisteredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());

        RechargeMoneyEvent event = RechargeMoneyEvent.builder()
                .rechargingRequestId(command.getRechargingRequestId())
                .membershipId(command.getMembershipId())
                .amount(command.getAmount())
                .bankingAccountAggregateIdentifier("test-bank-account-aggregate-identifier")
                .bankName("woori")
                .bankAccountNumber("123412341234")
                .build();

        apply(event);
    }

    @EventSourcingHandler
    public void on(CreateMemberMoneyEvent event) {
        System.out.println("CreateMemberMoneyEvent Sourcing Handler");

        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    @CommandHandler
    public String handle(@NotNull IncreaseMemberMoneyCommand command) {
        System.out.println("Increase money command Handler");
        id = command.getId();

        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getBalance()));
        return id;
    }

    @EventSourcingHandler
    public void on(IncreaseMemberMoneyEvent event) {
        System.out.println("IncreaseMemberMoney event sourcing Handler");

        id = event.getId();
        membershipId = event.getMembershipId();
        balance = event.getBalance();
    }
}
