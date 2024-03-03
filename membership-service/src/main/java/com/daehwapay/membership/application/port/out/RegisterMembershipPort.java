package com.daehwapay.membership.application.port.out;

import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;

public interface RegisterMembershipPort {
    MembershipEntity createMembership(String name,
                                      String email,
                                      String address,
                                      boolean valid,
                                      boolean corporation);
}
