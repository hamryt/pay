package com.daehwapay.bankingservice.application.port.in;

import com.daehwapay.bankingservice.adapter.axon.command.UpdateRequestFirmbankingCommand;
import com.daehwapay.bankingservice.domain.RequestFirmbanking;

public interface RequestFirmbankingUseCase {
    RequestFirmbanking registerFirmbanking(RequestFirmbankingCommand command);

    void requestFirmbankingByEvent(RequestFirmbankingCommand command);

    void updateFirmbankingByEvent(UpdateRequestFirmbankingCommand command);
}
