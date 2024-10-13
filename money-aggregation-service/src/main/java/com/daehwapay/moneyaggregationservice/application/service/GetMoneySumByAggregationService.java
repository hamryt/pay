package com.daehwapay.moneyaggregationservice.application.service;

import com.daehwapay.common.UseCase;
import com.daehwapay.moneyaggregationservice.adapter.out.service.MemberMoney;
import com.daehwapay.moneyaggregationservice.application.port.in.GetMoneySumByAddressCommand;
import com.daehwapay.moneyaggregationservice.application.port.in.GetMoneySumByAddressUseCase;
import com.daehwapay.moneyaggregationservice.application.port.out.GetMembershipPort;
import com.daehwapay.moneyaggregationservice.application.port.out.GetMoneySumPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UseCase
@RequiredArgsConstructor
public class GetMoneySumByAggregationService implements GetMoneySumByAddressUseCase {
    private final GetMembershipPort getMembershipPort;
    private final GetMoneySumPort getMoneySumPort;

    @Override
    public int getMoneySumByAddress(GetMoneySumByAddressCommand command) {
        String targetAddress = command.getAddress();
        List<Long> membershipIds = getMembershipPort.getMembershipsByAddress(targetAddress)
                .stream().map(Long::parseLong).collect(Collectors.toList());

        List<List<Long>> membershipPartitionList = null;
        if (membershipIds.size() > 100) {
            membershipPartitionList = partitionList(membershipIds, 100);
        }

        int sum = 0;
        assert membershipPartitionList != null;
        for (List<Long> partitionedList : membershipPartitionList) {
            List<MemberMoney> memberMoneyList = getMoneySumPort.getMoneySumByMembershipIds(partitionedList);

            for (MemberMoney memberMoney : memberMoneyList) {
                sum += memberMoney.getBalance();
            }
        }

        return sum;
    }

    private static <T> List<List<T>> partitionList(List<T> list, int partitionSize) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / partitionSize))
                .values()
                .stream()
                .map(indices -> indices.stream().map(list::get).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
