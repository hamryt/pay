package com.daehwapay.moneyaggregationservice.application.port.in;

public interface GetMoneySumByAddressUseCase {
    int getMoneySumByAddress(GetMoneySumByAddressCommand command);
}
