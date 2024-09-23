package com.daehwapay.bankingservice.adapter.out.persistence;

import com.daehwapay.bankingservice.application.port.out.RequestFirmbankingPort;
import com.daehwapay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestFirmbankingAdapter implements RequestFirmbankingPort {

    private final RequestFirmbankingRepository repository;

    @Override
    public RequestFirmbankingEntity create(String fromBankName, String fromBankAccount, String toBankName, String toBankAccount, int moneyAccount, int firmbankingStatus, String aggregateIdentifier) {
        return repository.save(new RequestFirmbankingEntity(
                0L,
                fromBankName,
                fromBankAccount,
                toBankName,
                toBankAccount,
                moneyAccount,
                firmbankingStatus,
                UUID.randomUUID().toString(),
                aggregateIdentifier));
    }

    @Override
    public RequestFirmbankingEntity save(RequestFirmbankingEntity entity) {
        return repository.save(entity);
    }

    @Override
    public RequestFirmbankingEntity find(String aggregateIdentifier) {
        return repository.findFirstByAggregateIdentifier(aggregateIdentifier);
    }
}
