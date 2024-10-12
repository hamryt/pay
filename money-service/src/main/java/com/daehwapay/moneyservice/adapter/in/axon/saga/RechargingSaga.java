package com.daehwapay.moneyservice.adapter.in.axon.saga;

import com.daehwapay.common.command.CheckRegisteredBankAccountCommand;
import com.daehwapay.common.command.RequestFirmbankingCommand;
import com.daehwapay.common.command.RollbackFirmbankingRequestCommand;
import com.daehwapay.common.event.CheckedRegisteredBankAccountEvent;
import com.daehwapay.common.event.RequestFirmbankingFinishedEvent;
import com.daehwapay.common.event.RollbackFirmbankingFinishedEvent;
import com.daehwapay.moneyservice.adapter.in.axon.event.RechargeMoneyEvent;
import com.daehwapay.moneyservice.adapter.out.persistence.MemberMoneyEntity;
import com.daehwapay.moneyservice.application.port.out.IncreaseMoneyPort;
import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import jakarta.validation.constraints.NotNull;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
public class RechargingSaga {
    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(@NotNull CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handler(RechargeMoneyEvent event) {
        System.out.println("RechargingMoneyEvent saga start recharging request id: " + event.getRechargingRequestId());

        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId);

        commandGateway.send(
                CheckRegisteredBankAccountCommand.builder()
                        .aggregateIdentifier(event.getBankingAccountAggregateIdentifier())
                        .rechargingRequestId(event.getRechargingRequestId())
                        .checkRegisteredBankAccountId(checkRegisteredBankAccountId)
                        .membershipId(event.getMembershipId())
                        .bankName(event.getBankName())
                        .bankAccountNumber(event.getBankAccountNumber())
                        .amount(event.getAmount())
                        .build()
        ).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                        System.out.println("CheckRegisteredBankAccountCommand Command failed");
                    } else {
                        System.out.println("CheckRegisteredBankAccountCommand Command success");
                    }
                }
        );
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handler(CheckedRegisteredBankAccountEvent event) {
        System.out.println("CheckedRegisteredBankAccountEvent saga: " + event.getCheckRegisteredBankAccountId());
        boolean status = event.isChecked();
        if (status) {
            System.out.println("CheckedRegisteredBankAccountEvent event success");
        } else {
            System.out.println("CheckedRegisteredBankAccountEvent event failed");
        }

        String requestFirmbankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmbankingId", requestFirmbankingId);

        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .requestFirmbankingId(requestFirmbankingId)
                .aggregateIdentifier(event.getFirmbankingRequestAggregateIdentifier())
                .rechargingRequestId(event.getRechargingRequestId())
                .membershipId(event.getMembershipId())
                .fromBankName(event.getFromBankName())
                .fromBankAccountNumber(event.getFromBankAccountNumber())
                .toBankName("kakao bank")
                .toBankAccountNumber("1234-1234-1234")
                .amount(event.getAmount())
                .build();

        commandGateway.send(command);
    }

    @SagaEventHandler(associationProperty = "requestFirmbankingId")
    public void handler(RequestFirmbankingFinishedEvent event, IncreaseMoneyPort port) {
        System.out.println("RequestFirmbankingFinishedEvent saga: " + event.toString());

        boolean status = event.getStatus() == 0;
        if (status) {
            System.out.println("RequestFirmbankingFinishedEvent event success");
        } else {
            System.out.println("RequestFirmbankingFinishedEvent event failed");
        }

        MemberMoneyEntity entity = port.increaseMoney(Long.parseLong(event.getMembershipId()), event.getAmount());
        port.createMoneyChange(
                Long.parseLong(event.getMembershipId()),
                ChangingType.from(event.getAmount()),
                event.getAmount(),
                true,
                ChangingMoneyStatus.from(event.getStatus()),
                UUID.randomUUID());

        if (entity == null) {
            String rollbackFirmbankingId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackFirmbankingId", rollbackFirmbankingId);

            RollbackFirmbankingRequestCommand command = RollbackFirmbankingRequestCommand.builder()
                    .rollbackFirmbankingId(rollbackFirmbankingId)
                    .aggregateIdentifier(event.getRequestFirmbankingAggregateIdentifier())
                    .rechargeRequestId(event.getRechargingRequestId())
                    .membershipId(event.getMembershipId())
                    .bankName(event.getFromBankName())
                    .bankAccountNumber(event.getFromBankAccountNumber())
                    .moneyAmount(event.getAmount())
                    .build();

            commandGateway.send(command);
        } else {
            System.out.println("!!!!!!!!!!!!!!saga end!!!!!!!!!!!!!!!");
            SagaLifecycle.end();
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmbankingId")
    public void handle(RollbackFirmbankingFinishedEvent event) {
        System.out.println("RollbackFirmbankingFinishedEvent saga: " + event.toString());
    }
}
