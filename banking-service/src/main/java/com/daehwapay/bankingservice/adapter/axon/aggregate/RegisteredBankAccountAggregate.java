package com.daehwapay.bankingservice.adapter.axon.aggregate;

import com.daehwapay.bankingservice.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.daehwapay.bankingservice.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.daehwapay.bankingservice.adapter.out.external.bank.BankAccount;
import com.daehwapay.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.daehwapay.bankingservice.application.port.out.RegisterBankAccountPort;
import com.daehwapay.common.command.CheckRegisteredBankAccountCommand;
import com.daehwapay.common.event.CheckedRegisteredBankAccountEvent;
import jakarta.validation.constraints.NotNull;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class RegisteredBankAccountAggregate {
    @AggregateIdentifier
    private String id;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {
        System.out.println("CreateRegisteredBankAccountCommand Handler");

        // store event
        apply(new CreateRegisteredBankAccountEvent(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()));
    }

    @CommandHandler
    public void handle(CheckRegisteredBankAccountCommand command, RegisterBankAccountPort port) {
        System.out.println("CheckRegisteredBankAccountCommand handler");
        id = command.getAggregateIdentifier();

        BankAccount bankAccount = port.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        String firmbankingUUID = UUID.randomUUID().toString();

        apply(CheckedRegisteredBankAccountEvent.builder()
                .rechargingRequestId(command.getRechargingRequestId())
                .checkRegisteredBankAccountId(command.getCheckRegisteredBankAccountId())
                .membershipId(command.getMembershipId())
                .isChecked(bankAccount.isValid())
                .amount(command.getAmount())
                .firmbankingRequestAggregateIdentifier(firmbankingUUID)
                .toBankName(bankAccount.getBankName())
                .toBankAccountNumber(bankAccount.getBankAccountNumber())
                .build());
    }

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event) {
        System.out.println("CreateRegisteredBankAccountEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankName = event.getBankName();
        bankAccountNumber = event.getBankAccountNumber();
    }
}
