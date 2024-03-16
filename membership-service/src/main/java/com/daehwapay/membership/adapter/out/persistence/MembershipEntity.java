package com.daehwapay.membership.adapter.out.persistence;


import com.daehwapay.membership.domain.Membership;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "membership")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long membershipId = 0L;
    private String name;
    private String email;
    private String address;
    private boolean isValid;
    private boolean isCorporation;

    public MembershipEntity(String name, String email, String address, boolean isValid, boolean isCorporation) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorporation = isCorporation;
    }
}
