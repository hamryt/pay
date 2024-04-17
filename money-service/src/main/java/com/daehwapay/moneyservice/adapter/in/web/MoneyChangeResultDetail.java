package com.daehwapay.moneyservice.adapter.in.web;

import com.daehwapay.moneyservice.enums.ChangingType;
import com.daehwapay.moneyservice.enums.MoneyChangingResultStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MoneyChangeResultDetail {
    private Long moneyChangingRequestId;
    private ChangingType moneyChangeType;
    private MoneyChangingResultStatus moneyChangingType;
    private int amount;
}
