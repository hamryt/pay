package com.daehwapay.bankingservice.adapter.axon.aggregate;

import com.daehwapay.bankingservice.adapter.axon.command.CreateRequestFirmbankingCommand;
import com.daehwapay.bankingservice.adapter.axon.event.RequestFirmbankingCreatedEvent;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Table(name = "request_firmbanking")
@Aggregate
@Getter
@Setter
@NoArgsConstructor
public class RequestFirmBankingAggregate {
    @AggregateIdentifier
    private String id;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
    private int firmbankingStatus;

    @CommandHandler
    public RequestFirmBankingAggregate(CreateRequestFirmbankingCommand command) {
        System.out.println("CreateRequestFirmbankingCommand handler");

        RequestFirmbankingCreatedEvent event = RequestFirmbankingCreatedEvent.builder()
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .moneyAmount(command.getMoneyAmount())
                .build();

        apply(event);
    }

    @EventSourcingHandler
    public void on(RequestFirmbankingCreatedEvent event) {
        System.out.println("RequestFirmbankingCreatedEvent Sourcing Handler");

        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
    }
}
