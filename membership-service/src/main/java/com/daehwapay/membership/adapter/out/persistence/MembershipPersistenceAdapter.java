package com.daehwapay.membership.adapter.out.persistence;

import com.daehwapay.membership.application.port.out.FindMembershipPort;
import com.daehwapay.membership.application.port.out.RegisterMembershipPort;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort {

    private final MembershipRepository membershipRepository;

    @Override
    public MembershipEntity createMembership(String name, String email, String address, boolean valid, boolean corporation) {
        return membershipRepository.save(
                new MembershipEntity(name, email, address, valid, corporation)
        );
    }

    @Override
    public MembershipEntity findMembership(Long membershipId) {
        return membershipRepository.getReferenceById(membershipId);
    }
}
