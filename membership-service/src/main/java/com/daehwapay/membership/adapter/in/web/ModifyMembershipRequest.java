package com.daehwapay.membership.adapter.in.web;

import lombok.Getter;

@Getter
public class ModifyMembershipRequest {
    private String name;
    private String address;
    private String email;
    private boolean corporation;
    private boolean valid;
}
