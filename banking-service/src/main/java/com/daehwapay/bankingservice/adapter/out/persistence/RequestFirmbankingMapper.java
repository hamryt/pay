package com.daehwapay.bankingservice.adapter.out.persistence;

import com.daehwapay.bankingservice.domain.RequestFirmbanking;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestFirmbankingMapper {
    public RequestFirmbanking entityToDomain(RequestFirmbankingEntity entity, UUID uuid) {
        return RequestFirmbanking.generate(
                entity.getId(),
                entity.getFromBankName(),
                entity.getFromBankAccount(),
                entity.getToBankName(),
                entity.getToBankAccount(),
                entity.getMoneyAccount(),
                entity.getFirmbankingStatus(),
                uuid
        );
    }
}
