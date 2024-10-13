package com.daehwapay.moneyaggregationservice.adapter.out.service;

import com.daehwapay.common.ExternalSystemAdapter;
import com.daehwapay.common.command.CommonHttpClient;
import com.daehwapay.moneyaggregationservice.application.port.out.GetMoneySumPort;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceAdapter implements GetMoneySumPort {
    private final CommonHttpClient httpClient;
    @Value("${service.money.url}")
    private String moneyServiceUrl;


    @Override
    public List<MemberMoney> getMoneySumByMembershipIds(List<Long> membershipIds) {
        String url = String.join("/", moneyServiceUrl, "money/member-money");
        ObjectMapper mapper = new ObjectMapper();

        try {
            FindMemberMoneyRequest request = new FindMemberMoneyRequest(membershipIds);
            String jsonResponse = httpClient.sendPostRequest(url, mapper.writeValueAsString(request)).body();
            return mapper.readValue(jsonResponse, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
