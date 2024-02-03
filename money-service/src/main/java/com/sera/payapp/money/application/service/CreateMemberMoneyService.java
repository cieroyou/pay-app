package com.sera.payapp.money.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyCommand;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateMemberMoneyService implements CreateMemberMoneyUseCase {
    @Override
    public void createMemberMoney(CreateMemberMoneyCommand createMemberMoneyCommand) {

    }
}
