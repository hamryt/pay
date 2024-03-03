package com.daehwapay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final Long membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean valid;
    private final boolean corporation;

    public static Membership generate(Long membershipId,
                                      String name,
                                      String email,
                                      String address,
                                      boolean valid,
                                      boolean corporation) {
        return new Membership(membershipId, name, email, address, valid, corporation);
    }
}
