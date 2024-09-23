package com.daehwapay.bankingservice.application.servie;

import com.daehwapay.bankingservice.adapter.axon.command.CreateRequestFirmbankingCommand;
import com.daehwapay.bankingservice.adapter.axon.command.UpdateRequestFirmbankingCommand;
import com.daehwapay.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.daehwapay.bankingservice.adapter.out.external.bank.FirmbankingResult;
import com.daehwapay.bankingservice.adapter.out.persistence.RequestFirmbankingEntity;
import com.daehwapay.bankingservice.adapter.out.persistence.RequestFirmbankingMapper;
import com.daehwapay.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.daehwapay.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.daehwapay.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.daehwapay.bankingservice.application.port.out.RequestFirmbankingPort;
import com.daehwapay.bankingservice.domain.RequestFirmbanking;
import com.daehwapay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase {

    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final RequestFirmbankingMapper mapper;
    private final CommandGateway commandGateway;

    @Override
    public RequestFirmbanking registerFirmbanking(RequestFirmbankingCommand command) {
        RequestFirmbankingEntity requestFirmbankingEntity = requestFirmbankingPort.create(command.getFromBankName(),
                command.getFromBankAccount(),
                command.getToBankName(),
                command.getToBankAccount(),
                command.getMoneyAmount(),
                0,
                null);

        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccount(),
                        command.getToBankName(),
                        command.getToBankAccount()
                ));

        if (result.getResultCode() == 1) {
            requestFirmbankingEntity.setFirmbankingStatus(1);
        } else {
            requestFirmbankingEntity.setFirmbankingStatus(0);
        }

        return mapper.entityToDomain(requestFirmbankingPort.save(requestFirmbankingEntity), UUID.randomUUID());
    }

    @Override
    public void requestFirmbankingByEvent(RequestFirmbankingCommand command) {
        commandGateway.send(new CreateRequestFirmbankingCommand(
                command.getFromBankName(),
                command.getFromBankAccount(),
                command.getToBankName(),
                command.getToBankAccount(),
                command.getMoneyAmount()
        )).whenComplete((Object result, Throwable throwable) -> {
            if (throwable == null) {
                System.out.println("updateFirmbankingByEvent Aggregate ID:" + result.toString());

                RequestFirmbankingEntity entity = requestFirmbankingPort.create(
                        command.getFromBankName(),
                        command.getFromBankAccount(),
                        command.getToBankName(),
                        command.getToBankAccount(),
                        command.getMoneyAmount(),
                        0,
                        result.toString()
                );

                FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(
                        new ExternalFirmbankingRequest(
                                command.getFromBankName(),
                                command.getFromBankAccount(),
                                command.getToBankName(),
                                command.getToBankAccount()
                        ));

                UUID randomUUID = UUID.randomUUID();
                entity.setUuid(randomUUID.toString());

                if (firmbankingResult.getResultCode() == 0) {
                    // 성공
                    entity.setFirmbankingStatus(1);
                } else {
                    // 실패
                    entity.setFirmbankingStatus(2);
                }

                requestFirmbankingPort.save(entity);
            } else {
                throwable.printStackTrace();
                System.out.println("error:" + throwable.getMessage());
            }
        });
    }

    @Override
    public void updateFirmbankingByEvent(UpdateRequestFirmbankingCommand command) {
        commandGateway.send(command)
                .whenComplete((result, throwable) -> {
                    if (throwable == null) {
                        System.out.println("update firmbanking by event aggregate id:" + result.toString());
                        RequestFirmbankingEntity entity = requestFirmbankingPort.find(result.toString());

                        entity.setFirmbankingStatus(command.getFirmbankingStatus());
                        requestFirmbankingPort.save(entity);
                    } else {
                        System.out.println("error: " + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });
    }
}
