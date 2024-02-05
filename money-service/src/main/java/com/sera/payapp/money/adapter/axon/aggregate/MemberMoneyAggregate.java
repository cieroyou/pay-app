package com.sera.payapp.money.adapter.axon.aggregate;

import com.sera.payapp.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.sera.payapp.money.adapter.axon.command.MemberMoneyIncreasedCommand;
import com.sera.payapp.money.adapter.axon.event.MemberMoneyCreatedEvent;
import com.sera.payapp.money.adapter.axon.event.MemberMoneyIncreasedEvent;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * MemberMoney 변경하는 모든 행위는 이 Aggregate 에서 이루어짐
 * 생성, 머니 증갱, 머니 감액
 */
@Table(name = "member_money")
@Aggregate
@Slf4j
@Getter
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    // commandGateway.send() 를 통해 Command 가 들어오면
    // 해당 command를 핸들링 하는 생성자
    // 여기에서 Event 를 만들어 Event 를 저장함 그 다음 행위는 EventSourcingHandler 에서 이루어짐
    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {

        String id = UUID.randomUUID().toString();
        log.info("CreateMoneyCommand Handler, id: {}, command: {}", id, command);

        // store event
        apply(new MemberMoneyCreatedEvent(id,
                command.getMembershipId()));
    }

    @CommandHandler
    public String handle(MemberMoneyIncreasedCommand command) {
        log.info("IncreaseMoneyCommand Handler, command: {}", command);
        String id = command.getAggregateIdentifier();
        apply(new MemberMoneyIncreasedEvent(
                id,
                command.getTargetMembershipId(),
                command.getAmount())
        );
        return id;
    }

    /**
     * CommandHandler를 통해 받은 event를 가지고, MemberMoneyAggregate 를 생성함
     * 이벤트 소싱이 완료되면, commandGateway.send().whenComplete() 에 결과 전달
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        log.info("CreateMoneyEvent Sourcing Handler, event: {}", event);
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(MemberMoneyIncreasedEvent event) {
        log.info("IncreasedMoneyEvent Sourcing Handler, event: {}", event);
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

    protected MemberMoneyAggregate() {
        // Required by Axon to construct an empty instance to initiate Event Sourcing.
    }
}
