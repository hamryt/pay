package com.daehwapay.membership.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.membership.application.port.in.FindMembershipsByAddressCommand;
import com.daehwapay.membership.application.port.in.FindMembershipCommand;
import com.daehwapay.membership.application.port.in.FindMembershipUseCase;
import com.daehwapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<Membership> findMembership(@PathVariable Long membershipId) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();

        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }

    @GetMapping(path = "/membership/address/{address}")
    ResponseEntity<List<Membership>> findMembershipsListByAddress(@PathVariable String address) {
        FindMembershipsByAddressCommand command = FindMembershipsByAddressCommand.builder()
                .address(address)
                .build();

        return ResponseEntity.ok(findMembershipUseCase.findMembershipsByAddress(command));
    }
}
