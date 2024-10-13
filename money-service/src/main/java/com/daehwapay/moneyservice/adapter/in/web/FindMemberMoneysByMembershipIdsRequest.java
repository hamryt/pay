package com.daehwapay.moneyservice.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindMemberMoneysByMembershipIdsRequest {
    private List<Long> membershipIds;
}
