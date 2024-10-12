package com.daehwapay.bankingservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_firmbanking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFirmbankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromBankName;
    private String fromBankAccount;
    private String toBankName;
    private String toBankAccount;
    private int moneyAmount;
    private int firmbankingStatus; // 0:요청, 1:완료, 2:실패
    private String uuid;
    private String aggregateIdentifier;
}
