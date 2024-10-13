package com.daehwapay.moneyaggregationservice.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.moneyaggregationservice.application.port.in.GetMoneySumByAddressCommand;
import com.daehwapay.moneyaggregationservice.application.port.in.GetMoneySumByAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping
@RequiredArgsConstructor
public class GetMoneySumController {
    private final GetMoneySumByAddressUseCase useCase;

    @PostMapping(path = "/money/aggration/get-money-sum-by-address")
    int getMoneySumByAddress(@RequestBody GetMoneySumByAddressRequest request) {
        GetMoneySumByAddressCommand command = GetMoneySumByAddressCommand.builder()
                .address(request.getAddress())
                .build();

        return useCase.getMoneySumByAddress(command);
    }
}
