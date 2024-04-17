package com.daehwapay.bankingservice.adapter.in.web;

import com.daehwapay.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.daehwapay.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.daehwapay.bankingservice.domain.RequestFirmbanking;
import com.daehwapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase useCase;

    @PostMapping("/banking/firmbanking/request")
    ResponseEntity<RequestFirmbanking> registerMembership(@RequestBody RequestFirmbankingRequest request) {

        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccount(request.getFromBankAccount())
                .toBankName(request.getToBankName())
                .toBankAccount(request.getToBankAccount())
                .moneyAmount(request.getMoneyAmount())
                .build();

        return ResponseEntity.ok(useCase.registerFirmbanking(command));
    }
}
