package com.sera.payapp.money.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyCommand;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyUseCase;
import com.sera.payapp.money.application.port.out.CreateMemberMoneyPort;
import com.sera.payapp.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class CreateMemberMoneyService implements CreateMemberMoneyUseCase {
    private final CommandGateway commandGateway;
    private final CreateMemberMoneyPort createMemberMoneyPort;

    @Override
    public void createMemberMoney(CreateMemberMoneyCommand createMemberMoneyCommand) {
        // axon 을 이용하여 Command 를 EventQueue 에 넣는 행위
        MemberMoneyCreatedCommand axonCommand =
                new MemberMoneyCreatedCommand(createMemberMoneyCommand.getTargetMembershipId());
        // axonCommand 를 EventQueue 에 넣음
        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                // 에러 처리
                log.error("CreateMemberMoneyService.createMemberMoney() failed, throwable: " + throwable);
                throw new RuntimeException(throwable);
            } else {
                // 정상 처리
                log.info("CreateMemberMoneyService.createMemberMoney() success, result: {}", result);
                createMemberMoneyPort.createMemberMoney(
                        new MemberMoney.MembershipId(createMemberMoneyCommand.getTargetMembershipId()),
                        new MemberMoney.MoneyAggregateIdentifier(result.toString())
                );
            }
        });
    }
}
