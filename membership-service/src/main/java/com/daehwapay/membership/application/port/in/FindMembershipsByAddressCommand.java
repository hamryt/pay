package com.daehwapay.membership.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindMembershipsByAddressCommand {
    private String address;
}
