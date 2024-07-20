package com.daehwapay.moneyservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "member_money")
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class MemberMoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    private Long membershipId;
    private int balance;
    private String aggregateIdentifier;
    public MemberMoneyEntity(Long membershipId, int balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }
}
