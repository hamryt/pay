package com.daehwapay.moneyservice.application.port.in;

import com.daehwapay.moneyservice.domain.MemberMoney;

import java.util.List;

public interface GetMemberMoneyUseCase {
    List<MemberMoney> findMemberMoneysByMembershipIds(FindMemberMoneysByMembershipIdsCommand command);
}
