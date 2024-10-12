package com.daehwapay.moneyservice.application.port.in;

import com.daehwapay.moneyservice.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoney(IncreaseMoneyRequestCommand command);
    MoneyChangingRequest increaseMoneyAsync(IncreaseMoneyRequestCommand command);

    void increaseMemberMoney(IncreaseMoneyRequestCommand command);

    void updateMoneyByEvent(IncreaseMoneyCommand command);
}
