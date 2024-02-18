package com.daehwapay.membership.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Member;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean valid;
    private final boolean corporation;

    public static Membership generate(String membershipId,
                                      String name,
                                      String email,
                                      String address,
                                      boolean valid,
                                      boolean corporation) {
        return new Membership(membershipId, name, email, address, valid, corporation);
    }
}
