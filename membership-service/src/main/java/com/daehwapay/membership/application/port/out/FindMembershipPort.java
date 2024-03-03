package com.daehwapay.membership.application.port.out;

import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;

import java.util.Optional;

public interface FindMembershipPort {
    MembershipEntity findMembership(Long membershipId);
}
