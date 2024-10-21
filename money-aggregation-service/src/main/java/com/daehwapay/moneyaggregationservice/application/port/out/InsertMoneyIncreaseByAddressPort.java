package com.daehwapay.moneyaggregationservice.application.port.out;

public interface InsertMoneyIncreaseByAddressPort {
    void insertMoney(String address, int moneyIncrease);
}
