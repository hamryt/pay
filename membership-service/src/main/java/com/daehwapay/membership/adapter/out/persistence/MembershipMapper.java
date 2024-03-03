package com.daehwapay.membership.adapter.out.persistence;

import com.daehwapay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipEntity membership) {
        return Membership.generate(
                membership.getMembershipId(),
                membership.getName(),
                membership.getEmail(),
                membership.getAddress(),
                membership.isValid(),
                membership.isCorporation()
        );
    }
}
