package com.daehwapay.bankingservice.application.port.in;

import com.daehwapay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {
    @NotNull
    private final String membershipId;
    @NotNull
    private final String bankName;
    @NotNull
    private final String bankAccountNumber;

    private final boolean linkedStatusValid;
}
