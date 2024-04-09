package com.daehwapay.bankingservice.application.port.out;

import com.daehwapay.bankingservice.adapter.out.persistence.RequestFirmbankingEntity;

public interface RequestFirmbankingPort {
    RequestFirmbankingEntity create(
            String fromBankName,
            String fromBankAccount,
            String toBankName,
            String toBankAccount,
            int moneyAccount,
            int firmbankingStatus
    );

    RequestFirmbankingEntity save(RequestFirmbankingEntity entity);
}
