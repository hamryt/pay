package com.daehwapay.moneyservice.application.port.in;


import com.daehwapay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {
    @NotNull
    private final Long targetMembershipId;

    @NotNull
    private final int amount;

    @NotNull
    private final boolean corporation;
}
