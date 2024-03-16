package com.daehwapay.membership.application.service;

import com.daehwapay.common.UseCase;
import com.daehwapay.membership.adapter.out.persistence.MembershipEntity;
import com.daehwapay.membership.adapter.out.persistence.MembershipMapper;
import com.daehwapay.membership.application.port.in.RegisterMembershipCommand;
import com.daehwapay.membership.application.port.in.RegisterMembershipUseCase;
import com.daehwapay.membership.application.port.out.RegisterMembershipPort;
import com.daehwapay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    @Transactional
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipEntity membershipEntity = registerMembershipPort.createMembership(command.getName(), command.getEmail(), command.getAddress(), command.isValid(), command.isCorporation());

        return membershipMapper.mapToDomainEntity(membershipEntity);
    }
}
