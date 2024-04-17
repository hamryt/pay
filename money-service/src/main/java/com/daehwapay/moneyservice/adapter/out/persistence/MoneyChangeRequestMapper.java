package com.daehwapay.moneyservice.adapter.out.persistence;

import com.daehwapay.moneyservice.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangeRequestMapper {
    public MoneyChangingRequest entityToDomain(MoneyChangeRequestEntity entity) {
        return MoneyChangingRequest.builder()
                .moneyChangingRequestId(entity.getMoneyChangeRequestId())
                .targetMembershipId(entity.getTargetMembershipId())
                .moneyChangeType(entity.getMoneyChangingType())
                .moneyAmount(entity.getMoneyAmount())
                .changingMoneyStatus(entity.getChangingMoneyStatus())
                .corporation(entity.isCorporation())
                .uuid(entity.getUuid())
                .createdAt(entity.getTimestamp().toLocalDateTime())
                .build();
    }
}
