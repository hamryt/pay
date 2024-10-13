package com.daehwapay.membership.adapter.out.persistence;

import com.daehwapay.common.PersistenceAdapter;
import com.daehwapay.membership.application.port.out.FindMembershipPort;
import com.daehwapay.membership.application.port.out.SaveMembershipPort;
import com.daehwapay.membership.application.port.out.RegisterMembershipPort;
import com.daehwapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort, RegisterMembershipPort, FindMembershipPort {

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

    @Override
    public List<MembershipEntity> findByAddress(String address) {
        return membershipRepository.findByAddress(address);
    }

    @Override
    public MembershipEntity saveMembership(MembershipEntity entity) {
        return membershipRepository.save(entity);
    }
}
