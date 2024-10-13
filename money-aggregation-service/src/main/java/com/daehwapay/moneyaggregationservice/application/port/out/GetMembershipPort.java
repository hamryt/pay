package com.daehwapay.moneyaggregationservice.application.port.out;

import java.util.List;

public interface GetMembershipPort {
    List<String> getMembershipsByAddress(String address);
}
