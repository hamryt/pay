package com.daehwapay.membership.application.port.out;

import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;
import com.daehwapay.membership.domain.Membership;

import java.util.List;
import java.util.Optional;

public interface FindMembershipPort {
    MembershipEntity findMembership(Long membershipId);

    List<MembershipEntity> findByAddress(String address);
}
