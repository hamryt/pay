package com.daehwapay.bankingservice.adapter.in.web;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateFirmbankingRequest {
    private String firmbankingAggregateIdentifier;
    private int status;
}
