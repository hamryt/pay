package com.daehwapay.membership.application.port.in;


import com.daehwapay.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {
    @NotNull
    private final Long membershipId;
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

    public ModifyMembershipCommand(Long membershipId, String name, String email, String address, boolean isValid, boolean isCorporation) {
        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorporation = isCorporation;

        this.validateSelf();
    }
}
