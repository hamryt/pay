package com.daehwapay.membership.application.service;

import com.daehwapay.common.UseCase;
import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;
import com.daehwapay.membership.adapter.out.persistence.MembershipMapper;
import com.daehwapay.membership.application.port.in.FindMembershipCommand;
import com.daehwapay.membership.application.port.in.FindMembershipUseCase;
import com.daehwapay.membership.application.port.out.FindMembershipPort;
import com.daehwapay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {
    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    @Transactional
    public Membership findMembership(FindMembershipCommand command) {
        MembershipEntity membershipEntity = findMembershipPort.findMembership(command.getMembershipId());
        return membershipMapper.entityToDomain(membershipEntity);
    }
}
