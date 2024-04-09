package com.daehwapay.bankingservice.adapter.out.external.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirmbankingResult {
    private int resultCode; // 0:성공, 1:실패

    public FirmbankingResult(int resultCode) {
        this.resultCode = resultCode;
    }
}
