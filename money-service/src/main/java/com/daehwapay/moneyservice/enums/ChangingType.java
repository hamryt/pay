package com.daehwapay.moneyservice.enums;


public enum ChangingType {
    INCREASE,
    DECREASE;

    public static ChangingType from(int amount) {
        if (amount > 0) {
            return INCREASE;
        }

        return DECREASE;
    }
}

