package com.daehwapay.membership.application.port.in;

import com.daehwapay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
    List<Membership> findMembershipsByAddress(FindMembershipsByAddressCommand command);
}
