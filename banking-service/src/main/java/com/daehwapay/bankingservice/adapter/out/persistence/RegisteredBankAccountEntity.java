package com.daehwapay.bankingservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registered_bank_account")
@Data
@NoArgsConstructor
public class RegisteredBankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registeredBankAccountId = 0L;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private String aggregateIdentifier;
    private boolean linkedStatusValid;

    public RegisteredBankAccountEntity(String membershipId, String bankName, String bankAccountNumber, String aggregateIdentifier, boolean linkedStatusValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.aggregateIdentifier = aggregateIdentifier;
        this.linkedStatusValid = linkedStatusValid;
    }
}
