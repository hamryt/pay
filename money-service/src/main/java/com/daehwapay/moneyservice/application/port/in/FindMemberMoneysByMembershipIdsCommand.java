package com.daehwapay.moneyservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindMemberMoneysByMembershipIdsCommand {
    private List<Long> membershipIds;
}
