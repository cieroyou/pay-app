package com.sera.payapp.money.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.money.adapter.axon.command.MemberMoneyIncreasedCommand;
import com.sera.payapp.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.sera.payapp.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.sera.payapp.money.application.port.in.IncreaseMoneyCommand;
import com.sera.payapp.money.application.port.in.IncreaseMoneyUseCase;
import com.sera.payapp.money.application.port.out.GetMemberMoneyPort;
import com.sera.payapp.money.application.port.out.IncreaseMoneyChangingRequestPort;
import com.sera.payapp.money.application.port.out.IncreaseMoneyPort;
import com.sera.payapp.money.domain.MemberMoney;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Primary;

@UseCase
@RequiredArgsConstructor
@Slf4j
@Primary
public class IncreaseMoneyByEventService implements IncreaseMoneyUseCase {
    private final GetMemberMoneyPort getMemberMoneyPort;
    private final IncreaseMoneyPort increaseMoneyPort;
    private final IncreaseMoneyChangingRequestPort increaseMoneyChangingRequestPort;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;


    private final CommandGateway commandGateway;

    @Override
    public MoneyChangingRequest increaseMoney(IncreaseMoneyCommand command) {
        MemberMoneyJpaEntity memberMoneyJpaEntity
                = getMemberMoneyPort.getMemberMoney(new MemberMoney.MembershipId(command.getTargetMembershipId()));
        String aggregateIdentifier = memberMoneyJpaEntity.getAggregateIdentifier();

        MemberMoneyIncreasedCommand axonCommand =
                new MemberMoneyIncreasedCommand(aggregateIdentifier, command.getTargetMembershipId(), command.getAmount());
        commandGateway.send(axonCommand)
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        log.error("IncreaseMoneyByEventService.increaseMoney() failed, throwable: " + throwable);
                        throw new RuntimeException(throwable);
                    } else {
                        log.info("IncreaseMoneyByEventService.increaseMoney() success, result: {}", result);
                        MemberMoneyJpaEntity memberMoney =
                                increaseMoneyPort.increaseMoney(new MemberMoney.MembershipId(command.getTargetMembershipId()), command.getAmount());

//                        if (memberMoney != null) {
//                            // MemberMoney 를 성공적으로 증액한 경우
//                            moneyChangingRequestMapper.mapToDomainEntity(increaseMoneyChangingRequestPort.increaseMoney(
//                                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
//                                    new MoneyChangingRequest.MoneyChangingType(ChangingMoneyType.INCREASING),
//                                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
//                                    new MoneyChangingRequest.LinkedStatusIsValid(true),
//                                    new MoneyChangingRequest.MoneyChangingStatus(ChangingMoneyStatus.SUCCESS),
//                                    new MoneyChangingRequest.Uuid(UUID.randomUUID())));
//                        }
                    }
                });
        return null;
    }
}
