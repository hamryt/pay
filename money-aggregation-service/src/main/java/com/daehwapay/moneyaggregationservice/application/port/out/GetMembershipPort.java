package com.daehwapay.moneyaggregationservice.application.port.out;

import com.daehwapay.moneyaggregationservice.adapter.out.service.Membership;

import java.util.List;

public interface GetMembershipPort {
    List<String> getMembershipsByAddress(String address);
    Membership getMembershipById(String membershipId);
}
