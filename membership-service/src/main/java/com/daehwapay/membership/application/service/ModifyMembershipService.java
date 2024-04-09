package com.daehwapay.membership.application.service;

import com.daehwapay.common.UseCase;
import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;
import com.daehwapay.membership.adapter.out.persistence.MembershipMapper;
import com.daehwapay.membership.application.port.in.ModifyMembershipCommand;
import com.daehwapay.membership.application.port.in.ModifyMembershipUseCase;
import com.daehwapay.membership.application.port.out.FindMembershipPort;
import com.daehwapay.membership.application.port.out.SaveMembershipPort;
import com.daehwapay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final SaveMembershipPort saveMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    @Transactional
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipEntity membership = findMembershipPort.findMembership(command.getMembershipId());

        membership.setName(command.getName());
        membership.setEmail(command.getEmail());
        membership.setAddress(command.getAddress());
        membership.setValid(command.isValid());
        membership.setCorporation(command.isCorporation());

        return membershipMapper.entityToDomain(saveMembershipPort.saveMembership(membership));
    }
}
