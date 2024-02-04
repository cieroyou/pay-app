package com.sera.payapp.money.adapter.axon.aggregate;

import com.sera.payapp.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.sera.payapp.money.adapter.axon.event.MemberMoneyCreatedEvent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@Slf4j
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    // commandGateway.send() 를 통해 Command 가 들어오면
    // 해당 command를 핸들링 하는 생성자
    // 여기에서 Event 를 만들어 Event 를 저장함 그 다음 행위는 EventSourcingHandler 에서 이루어짐
    @CommandHandler
    public MemberMoneyAggregate(@NotNull MemberMoneyCreatedCommand command) {
        log.info("CreateMoneyCommand Handler, command: {}", command);
        // store event
        apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
    }

    /**
     * CommandHandler를 통해 받은 event를 가지고, MemberMoneyAggregate 를 생성함
     * 이벤트 소싱이 완료되면, commandGateway.send().whenComplete() 에 결과 전달
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        log.info("CreateMoneyEvent Handler, event: {}", event);
        this.id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }
}
