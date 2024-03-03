package com.daehwapay.membership.application.port.in;

import common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
    @NotNull
    private final String name;
    @NotNull
    private final String email;
    @NotNull
    @NotBlank
    private final String address;
    @AssertTrue
    private final boolean isValid;
    private final boolean isCorporation;

    public RegisterMembershipCommand(String name, String email, String address, boolean isValid, boolean isCorporation) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorporation = isCorporation;

        this.validateSelf();
    }
}
