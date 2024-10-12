package com.daehwapay.moneyservice.enums;

public enum ChangingMoneyStatus {
    REQUIRED, SUCCEEDED, FAILED, CANCELED;

    public static ChangingMoneyStatus from(int status) {
        if (status == 0) {
            return SUCCEEDED;
        }

        return FAILED;
    }
}
