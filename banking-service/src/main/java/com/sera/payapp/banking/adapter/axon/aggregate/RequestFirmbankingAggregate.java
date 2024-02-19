package com.sera.payapp.banking.adapter.axon.aggregate;

import com.sera.payapp.banking.adapter.axon.command.RequestFirmbankingCreatedCommand;
import com.sera.payapp.banking.adapter.axon.command.RequestFirmbankingUpdatedCommand;
import com.sera.payapp.banking.adapter.axon.event.RequestFirmbankingCreatedEvent;
import com.sera.payapp.banking.adapter.axon.event.RequestFirmbankingUpdaedEvent;
import com.sera.payapp.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.sera.payapp.banking.adapter.out.external.bank.FirmbankingResult;
import com.sera.payapp.banking.application.port.out.RequestExternalFirmbankingPort;
import com.sera.payapp.banking.application.port.out.RequestFirmbankingPort;
import com.sera.payapp.banking.domain.FirmbankingRequest;
import com.sera.payapp.common.event.RequestFirmbankingCommand;
import com.sera.payapp.common.event.RequestFirmbankingFinishedEvent;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Table(name = "request_firmbanking")
@Aggregate
@Getter
@Slf4j
@NoArgsConstructor // Aggregate 에 필수
public class RequestFirmbankingAggregate {
    @AggregateIdentifier
    private String id;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;

    private int firmbankingStatus;

    @CommandHandler
    public RequestFirmbankingAggregate(RequestFirmbankingCreatedCommand command) {
        apply(RequestFirmbankingCreatedEvent.builder()
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .amount(command.getAmount())
                .build());
    }

    @CommandHandler
    public String handle(RequestFirmbankingUpdatedCommand command) {
        id = command.getAggregateIdentifier();

        // store event
        apply(new RequestFirmbankingUpdaedEvent(command.getFirmbankingStatus()));
        log.info("UpdateRequestFirmbankingCommand Handler, aggregateId: {}, command: {}", id, command);

        return id;
    }

    /**
     * 펌뱅킹정보 저장, 펌뱅킹 실행 후, 결과를 RequestFirmbankingFinishedEvent 로 전달
     *
     * @param command 펌뱅킹 요청 Command
     */
    @CommandHandler
    public RequestFirmbankingAggregate(RequestFirmbankingCommand command,
                                       RequestFirmbankingPort requestFirmbankingPort,
                                       RequestExternalFirmbankingPort externalFirmbankingPort) {
        id = command.getAggregateIdentifier();

        // RequestFirmbanking 데이터 디비 저장
        requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.Firmbankingstatus(0),
                new FirmbankingRequest.FirmbankingAggregateIdentifier(id)
        );

        // firmbanking!
        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        int resultCode = firmbankingResult.getResultCode();
        // 0. 성공, 1. 실패
        apply(new RequestFirmbankingFinishedEvent(
                command.getRequestFirmbankingId(),
                command.getRechargeRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                resultCode,
                id
        ));

    }

    @EventSourcingHandler
    public void on(RequestFirmbankingCreatedEvent event) {
        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
        moneyAmount = event.getAmount();
        log.info("RequestFirmbankingCreatedEvent Sourcing Handler, aggregateId: {}, event: {}", id, event);
    }


    @EventSourcingHandler
    public void on(RequestFirmbankingUpdaedEvent event) {
        firmbankingStatus = event.getFirmbankingStatus();
        log.info("UpdateRequestFirmbankingEvent Sourcing Handler, aggregateId: {}, event: {}", id, event);

    }


    @Override
    public String toString() {
        return "RequestFirmbankingAggregate{" +
                "id='" + id + '\'' +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", firmbankingStatus=" + firmbankingStatus +
                '}';
    }
}
