package com.daehwapay.moneyaggregationservice.adapter.out.service;

import com.daehwapay.common.ExternalSystemAdapter;
import com.daehwapay.common.command.CommonHttpClient;
import com.daehwapay.moneyaggregationservice.application.port.out.GetMembershipPort;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdapter implements GetMembershipPort {
    private final CommonHttpClient httpClient;
    @Value("${service.membership.url}")
    private String membershipServiceUrl;

    @Override
    public List<String> getMembershipsByAddress(String address) {
        String url = String.join("/", membershipServiceUrl, "membership/address", address);
        try {
            String jsonResponse = httpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            List<Membership> memberships = mapper.readValue(jsonResponse, new TypeReference<>() {});

            return memberships.stream()
                    .map(Membership::getMembershipId)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
