package com.daehwapay.moneyaggregationservice.adapter.in.web;

import com.daehwapay.common.WebAdapter;
import com.daehwapay.moneyaggregationservice.application.port.in.GetMoneySumByAddressCommand;
import com.daehwapay.moneyaggregationservice.application.port.in.GetMoneySumByAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class GetMoneySumController {
    private final GetMoneySumByAddressUseCase useCase;

    @GetMapping(path = "/money/aggration/get-money-sum/{address}")
    int aggregateMoneySumByAddress(@PathVariable String address) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        GetMoneySumByAddressCommand command = GetMoneySumByAddressCommand.builder()
                .address(address)
                .build();

        int result = useCase.getMoneySumByAddress(command);

        stopWatch.stop();
        System.out.println("경과 시간(밀리초): " + stopWatch.getTotalTimeMillis());

        return result;
    }

    @GetMapping(path = "/money/query/get-money-sum/{address}")
    long queryMoneySumByAddress(@PathVariable String address) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        GetMoneySumByAddressCommand command = GetMoneySumByAddressCommand.builder()
                .address(address)
                .build();

        long result = useCase.queryMoneySumByAddress(command);

        stopWatch.stop();
        System.out.println("경과 시간(밀리초): " + stopWatch.getTotalTimeMillis());

        return result;
    }
}
