package com.daehwapay.moneyservice.adapter.in.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMoneyCommand {
    @NotNull
    private String membershipId;
}
