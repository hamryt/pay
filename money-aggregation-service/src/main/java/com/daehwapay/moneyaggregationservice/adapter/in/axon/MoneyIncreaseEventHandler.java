package com.daehwapay.moneyaggregationservice.adapter.in.axon;

import com.daehwapay.common.event.RequestFirmbankingFinishedEvent;
import com.daehwapay.moneyaggregationservice.adapter.out.service.Membership;
import com.daehwapay.moneyaggregationservice.application.port.out.GetMembershipPort;
import com.daehwapay.moneyaggregationservice.application.port.out.InsertMoneyIncreaseByAddressPort;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class MoneyIncreaseEventHandler {
    @EventHandler
    public void handle(RequestFirmbankingFinishedEvent event, GetMembershipPort getMembershipPort, InsertMoneyIncreaseByAddressPort insertMoneyIncreaseByAddressPort) {
        System.out.println("Money Increase event handler Received: " + event.getRechargingRequestId());

        Membership membership = getMembershipPort.getMembershipById(event.getMembershipId());

        if (membership.getAddress().equals("강남구")) {
            System.out.println("DynamoDb insert" + membership.getAddress() + ", " + event.getAmount());

//            insertMoneyIncreaseByAddressPort.insertMoney(membership.getAddress(), event.getAmount());
        }
    }
}
