package com.daehwapay.membership.application.port.in;

import com.daehwapay.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
