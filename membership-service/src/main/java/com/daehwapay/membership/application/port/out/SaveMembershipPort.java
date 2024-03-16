package com.daehwapay.membership.application.port.out;

import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;

public interface SaveMembershipPort {
    MembershipEntity saveMembership(MembershipEntity entity);
}
