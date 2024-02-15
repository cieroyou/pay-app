package com.sera.payapp.banking.adapter.axon.aggregate;

import com.sera.payapp.banking.adapter.axon.command.RegisteredBankAccountCreatedCommand;
import com.sera.payapp.banking.adapter.axon.event.RegisteredBankAccountCreatedEvent;
import com.sera.payapp.banking.adapter.out.external.bank.BankAccountInfo;
import com.sera.payapp.banking.application.port.out.RequestBankAccountInfoPort;
import com.sera.payapp.common.event.CheckRegisteredBankAccountCommand;
import com.sera.payapp.common.event.CheckedRegisteredBankAccountEvent;
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

    // 외부에서 BankAccount 정상인지 체크될 때 무조건 해당 CommandHandler 를 통해 처리됨
    @CommandHandler
    public void handle(CheckRegisteredBankAccountCommand command, RequestBankAccountInfoPort bankAccountInfoPort) {
        log.info("Check RegisteredBankAccount Command Handler, command: {}", command);
        // 이 어그리거트(RegisteredBankAccount)가 정상인지를 확인한다.
        id = command.getAggregateIdentifier();

        // 외부 Bank API 호출을 통해 계좌가 정상인지 확인해야 함.
        String firmbankingUuid = UUID.randomUUID().toString();
        BankAccountInfo bankAccountInfo =
                bankAccountInfoPort.getBankAccountInfo(command.getBankName(), command.getBankAccountNumber());
        boolean isValidAccount = bankAccountInfo.isValid();

        apply(new CheckedRegisteredBankAccountEvent(
                command.getRechargeRequestId()
                , command.getCheckRegisteredBankAccountId()
                , command.getMembershipId()
                , isValidAccount
                , command.getAmount()
                , firmbankingUuid
                , bankAccountInfo.getBankName()
                , bankAccountInfo.getBankAccountNumber()
        ));


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
