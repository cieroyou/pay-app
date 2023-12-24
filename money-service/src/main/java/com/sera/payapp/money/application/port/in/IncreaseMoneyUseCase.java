package com.sera.payapp.money.application.port.in;

import com.sera.payapp.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyUseCase {
    MoneyChangingRequest increaseMoney(IncreaseMoneyCommand command);
}
