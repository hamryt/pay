package com.daehwapay.moneyservice.adapter.out.persistence;

import com.daehwapay.moneyservice.enums.ChangingMoneyStatus;
import com.daehwapay.moneyservice.enums.ChangingType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "money_change_request")
@Data
@NoArgsConstructor
public class MoneyChangeRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moneyChangeRequestId = 0L;
    private Long targetMembershipId;
    @Enumerated(EnumType.STRING)
    private ChangingType moneyChangingType;
    private int moneyAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;
    private boolean corporation;
    @Enumerated(EnumType.STRING)
    private ChangingMoneyStatus changingMoneyStatus;
    private UUID uuid;

    public MoneyChangeRequestEntity(
            Long targetMembershipId,
            ChangingType moneyChangingType,
            int moneyAmount,
            Timestamp timestamp,
            boolean corporation,
            ChangingMoneyStatus changingMoneyStatus,
            UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.timestamp = timestamp;
        this.corporation = corporation;
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid;
    }
}
