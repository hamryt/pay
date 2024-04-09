package com.daehwapay.bankingservice.adapter.out.persistence;

import com.daehwapay.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.daehwapay.bankingservice.adapter.out.external.bank.FirmbankingResult;
import com.daehwapay.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.daehwapay.bankingservice.application.port.out.RequestFirmbankingPort;
import com.daehwapay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestFirmbankingAdapter implements RequestFirmbankingPort {

    private final RequestFirmbankingRepository repository;

    @Override
    public RequestFirmbankingEntity create(String fromBankName, String fromBankAccount, String toBankName, String toBankAccount, int moneyAccount, int firmbankingStatus) {
        return repository.save(new RequestFirmbankingEntity(
                0L,
                fromBankName,
                fromBankAccount,
                toBankName,
                toBankAccount,
                moneyAccount,
                firmbankingStatus));
    }

    @Override
    public RequestFirmbankingEntity save(RequestFirmbankingEntity entity) {
        return repository.save(entity);
    }
}
