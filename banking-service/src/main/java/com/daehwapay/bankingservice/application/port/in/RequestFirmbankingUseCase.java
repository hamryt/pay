package com.daehwapay.bankingservice.application.port.in;

import com.daehwapay.bankingservice.domain.RequestFirmbanking;

public interface RequestFirmbankingUseCase {
    RequestFirmbanking registerFirmbanking(RequestFirmbankingCommand command);
}
