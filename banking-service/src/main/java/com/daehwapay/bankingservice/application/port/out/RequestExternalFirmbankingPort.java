package com.daehwapay.bankingservice.application.port.out;

import com.daehwapay.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.daehwapay.bankingservice.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
