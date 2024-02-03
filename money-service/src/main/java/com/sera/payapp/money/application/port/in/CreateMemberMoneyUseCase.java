package com.sera.payapp.money.application.port.in;

import com.sera.payapp.money.adapter.in.web.dto.CreateMemberMoneyCommand;

public interface CreateMemberMoneyUseCase {

    void createMemberMoney(CreateMemberMoneyCommand command);
}
