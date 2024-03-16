package com.daehwapay.membership.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.membership.application.port.in.RegisterMembershipCommand;
import com.daehwapay.membership.application.port.in.RegisterMembershipUseCase;
import com.daehwapay.membership.domain.Membership;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    ResponseEntity<Membership> registerMembership(@RequestBody RegisterMembershipRequest request) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorporation(request.isCorporation())
                .build();

        return ResponseEntity.ok(registerMembershipUseCase.registerMembership(command));
    }
}
