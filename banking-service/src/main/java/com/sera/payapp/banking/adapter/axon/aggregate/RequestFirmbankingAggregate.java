package com.sera.payapp.banking.adapter.axon.aggregate;

import com.sera.payapp.banking.adapter.axon.command.RequestFirmbankingCreatedCommand;
import com.sera.payapp.banking.adapter.axon.event.RequestFirmbankingCreatedEvent;
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
