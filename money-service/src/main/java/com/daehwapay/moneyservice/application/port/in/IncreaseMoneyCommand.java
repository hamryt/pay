package com.daehwapay.moneyservice.application.port.in;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncreaseMoneyCommand {
    private Long targetMembershipId;
    private int amount;
}
