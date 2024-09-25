package com.daehwapay.moneyservice.adapter.in.axon.saga;

import com.daehwapay.common.command.CheckRegisteredBankAccountCommand;
import com.daehwapay.common.event.CheckedRegisteredBankAccountEvent;
import com.daehwapay.moneyservice.adapter.in.axon.event.RechargeMoneyEvent;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
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
    }
}
