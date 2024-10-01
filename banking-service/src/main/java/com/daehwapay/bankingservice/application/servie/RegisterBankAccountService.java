package com.daehwapay.bankingservice.application.servie;

import com.daehwapay.bankingservice.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.daehwapay.bankingservice.adapter.out.external.bank.BankAccount;
import com.daehwapay.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.daehwapay.bankingservice.adapter.out.persistence.RegisteredBankAccountEntity;
import com.daehwapay.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.daehwapay.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.daehwapay.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.daehwapay.bankingservice.application.port.out.RegisterBankAccountPort;
import com.daehwapay.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.daehwapay.bankingservice.domain.RegisteredBankAccount;
import com.daehwapay.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final RegisteredBankAccountMapper mapper;
    private final CommandGateway commandGateway;

    @Override
    @Transactional
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        BankAccount bankAccount = requestBankAccountInfoPort.requestBankAccount(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));

        if (bankAccount == null) {
            throw new RuntimeException("존재하지 않는 계정입니다.");
        }

        RegisteredBankAccountEntity registeredBankAccount = registerBankAccountPort.register(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber(), UUID.randomUUID().toString(), command.isLinkedStatusValid());
        return mapper.entityToDomain(registeredBankAccount);
    }

    @Override
    public void registerBankAccountByEvent(RegisterBankAccountCommand command) {
        commandGateway.send(new CreateRegisteredBankAccountCommand(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber())).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        System.out.println("throwable = " + throwable);
                    }

                    registerBankAccountPort.register(
                            command.getMembershipId(), command.getBankName(),
                            command.getBankAccountNumber(), result.toString(), command.isLinkedStatusValid());
                }
        );
    }
}
