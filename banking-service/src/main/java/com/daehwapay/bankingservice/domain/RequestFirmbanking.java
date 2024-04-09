package com.daehwapay.bankingservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestFirmbanking {
    private final Long requestFirmbankingId;
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int moneyAccount;
    private final int firmbankingStatus; // 0:요청, 1:완료, 2:실패
    private final UUID uuid;

    public static RequestFirmbanking generate(Long requestFirmbankingId,
                                              String fromBankName,
                                              String fromBankAccountNumber,
                                              String toBankName,
                                              String toBankAccountNumber,
                                              int moneyAccount,
                                              int firmbankingStatus,
                                              UUID uuid
    ) {
        return RequestFirmbanking.builder()
                .requestFirmbankingId(requestFirmbankingId)
                .fromBankName(fromBankName)
                .fromBankAccountNumber(fromBankAccountNumber)
                .toBankName(toBankName)
                .toBankAccountNumber(toBankAccountNumber)
                .moneyAccount(moneyAccount)
                .firmbankingStatus(firmbankingStatus)
                .uuid(uuid)
                .build();
    }
}
