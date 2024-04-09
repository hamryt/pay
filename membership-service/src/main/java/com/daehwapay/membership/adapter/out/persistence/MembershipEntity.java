package com.daehwapay.membership.adapter.out.persistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@Data
@NoArgsConstructor
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long membershipId = 0L;
    private String name;
    private String email;
    private String address;
    private boolean valid;
    private boolean corporation;

    public MembershipEntity(String name, String email, String address, boolean isValid, boolean isCorporation) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.valid = isValid;
        this.corporation = isCorporation;
    }
}
