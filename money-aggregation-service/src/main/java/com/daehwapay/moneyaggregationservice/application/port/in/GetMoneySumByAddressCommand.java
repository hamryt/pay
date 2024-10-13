package com.daehwapay.moneyaggregationservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMoneySumByAddressCommand {
    private String address;
}
