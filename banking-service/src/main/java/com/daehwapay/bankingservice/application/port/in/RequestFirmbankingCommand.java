package com.daehwapay.bankingservice.application.port.in;

import com.daehwapay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RequestFirmbankingCommand extends SelfValidating<RequestFirmbankingCommand> {
    @NotNull
    private final String fromBankName;
    @NotNull
    private final String fromBankAccount;
    @NotNull
    private final String toBankName;
    @NotNull
    private final String toBankAccount;

    private final int moneyAmount;
}
