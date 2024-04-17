package com.daehwapay.bankingservice.application.servie;

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

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase {

    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final RequestFirmbankingMapper mapper;

    @Override
    public RequestFirmbanking registerFirmbanking(RequestFirmbankingCommand command) {
        RequestFirmbankingEntity requestFirmbankingEntity =  requestFirmbankingPort.create(command.getFromBankName(),
                command.getFromBankAccount(),
                command.getToBankName(),
                command.getToBankAccount(),
                command.getMoneyAmount(),
                0);

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
}
