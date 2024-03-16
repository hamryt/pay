package com.daehwapay.membership.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.membership.application.port.in.ModifyMembershipCommand;
import com.daehwapay.membership.application.port.in.ModifyMembershipUseCase;
import com.daehwapay.membership.domain.Membership;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase useCase;

    @PutMapping("/membership/{membershipId}")
    ResponseEntity<Membership> modifyMembership(
            @PathVariable long membershipId,
            @RequestBody ModifyMembershipRequest request
    ) {
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(membershipId)
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isValid(request.isValid())
                .isCorporation(request.isCorporation())
                .build();

        return ResponseEntity.ok(useCase.modifyMembership(command));
    }
}
