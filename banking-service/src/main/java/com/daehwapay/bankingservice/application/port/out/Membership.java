package com.daehwapay.bankingservice.application.port.out;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Membership {
    private Long membershipId;
    private String name;
    private String email;
    private String address;
    private boolean valid;
    private boolean corporation;
}
