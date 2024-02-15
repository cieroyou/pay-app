package com.sera.payapp.banking.adapter.axon.aggregate;

import com.sera.payapp.banking.adapter.axon.command.RegisteredBankAccountCreatedCommand;
import com.sera.payapp.banking.adapter.axon.event.RegisteredBankAccountCreatedEvent;
import com.sera.payapp.common.event.CheckRegisteredBankAccountCommand;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * RegisterdBankAccount(Money에 연결된 게좌) 도메인 Aggregate
 * RegisteredBankAccount 생성 처리
 * RegisteredBankAccount 업데이트 처리
 */
@Aggregate
@NoArgsConstructor
@Slf4j
public class RegisteredBankAccountAggregate {
    @AggregateIdentifier
    private String id;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(RegisteredBankAccountCreatedCommand command) {
        log.info("CreateRegisteredBankAccountCommand Handler, command: {}", command);
        apply(new RegisteredBankAccountCreatedEvent(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()));
    }

    public void handle(CheckRegisteredBankAccountCommand command) {
        log.info("Check RegisteredBankAccount Command Handler, command: {}", command);
        // 이 어그리거트(RegisteredBankAccount)가 정상인지를 확인한다.

    }

    @EventSourcingHandler
    public void on(RegisteredBankAccountCreatedEvent event) {
        log.info("RegisteredBankAccountCreatedEvent Sourcing Handler, event: {}", event);
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankName = event.getBankName();
        bankAccountNumber = event.getBankAccountNumber();
    }
}
