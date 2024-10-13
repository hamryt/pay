package com.daehwapay.membership.adapter.out.persistence;

import com.daehwapay.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    List<MembershipEntity> findByAddress(String address);
}
