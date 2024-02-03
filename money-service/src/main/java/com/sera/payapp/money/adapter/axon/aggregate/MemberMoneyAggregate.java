package com.sera.payapp.money.adapter.axon.aggregate;

import com.sera.payapp.money.adapter.axon.event.MemberMoneyCreatedEvent;
import com.sera.payapp.money.adapter.axon.command.MemberMoneyCreatedCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Getter
@Slf4j
//@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;
    private Long membershipId;
    private int balance;


    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
        log.info("MemberMoneyAggregate 생성자 호출");
        log.info("MemberMoneyCreatedCommand Handler 생성됨");

        apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
    }

    /**
     * MemberMoneyCreatedCommandHandler에서 apply 를 하면,
     * 이벤트소싱이 되어, MemberMoneyAggreate를 새롭게 생성한다.
     * @param event
     */
    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        log.info("MemberMoneyCreatedEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    protected MemberMoneyAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
}
