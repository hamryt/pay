package com.daehwapay.moneyservice.domain;

import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {
    private final Long moneyChangingRequestId;
    private final Long targetMembershipId;
    private final ChangingType moneyChangeType; // enum 0: 증액, 1: 감액
    private final int moneyAmount;
    private final ChangingMoneyStatus changingMoneyStatus; //
    private final boolean corporation;
    private final UUID uuid;
    private final LocalDateTime createdAt;
}
