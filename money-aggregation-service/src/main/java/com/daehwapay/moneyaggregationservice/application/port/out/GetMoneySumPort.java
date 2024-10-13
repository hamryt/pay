package com.daehwapay.moneyaggregationservice.application.port.out;

import com.daehwapay.moneyaggregationservice.adapter.out.service.MemberMoney;

import java.util.List;

public interface GetMoneySumPort {
    List<MemberMoney> getMoneySumByMembershipIds(List<Long> membershipIds);
}
