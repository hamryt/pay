package com.daehwapay.moneyservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "member_money")
@Entity
@Data
@NoArgsConstructor
public class MemberMoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    private Long membershipId;
    private int balance;

    public MemberMoneyEntity(Long membershipId, int balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }
}
