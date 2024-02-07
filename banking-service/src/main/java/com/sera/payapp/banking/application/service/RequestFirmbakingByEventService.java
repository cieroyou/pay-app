package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.axon.command.RequestFirmbankingCreatedCommand;
import com.sera.payapp.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.sera.payapp.banking.adapter.out.external.bank.FirmbankingResult;
import com.sera.payapp.banking.adapter.out.persistence.firmbanking_request.FirmbankingRequestJpaEntity;
import com.sera.payapp.banking.application.port.in.RequestFirmbankingCommand;
import com.sera.payapp.banking.application.port.in.RequestFirmbankingUseCase;
import com.sera.payapp.banking.application.port.out.RequestExternalFirmbankingPort;
import com.sera.payapp.banking.application.port.out.RequestFirmbankingPort;
import com.sera.payapp.banking.domain.FirmbankingRequest;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Primary;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@UseCase
@Primary
public class RequestFirmbakingByEventService implements RequestFirmbankingUseCase {

    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final CommandGateway commandGateway;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        var axonCommand = RequestFirmbankingCreatedCommand.builder()
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .amount(command.getMoneyAmount())
                .build();
        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("Firmbanking request failed", throwable);
            } else {
                String aggregateId = result.toString();
                log.info("Firmbanking request success, AggregateId: {}", aggregateId);

                // 1. firmbankingRequest 생성
                FirmbankingRequestJpaEntity firmbankingRequestJpaEntity = requestFirmbankingPort.createFirmbankingRequest(
                        new FirmbankingRequest.FirmbankingRequestId(UUID.randomUUID().toString()),
                        new FirmbankingRequest.FromBankName(command.getFromBankName()),
                        new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                        new FirmbankingRequest.ToBankName(command.getToBankName()),
                        new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                        new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                        new FirmbankingRequest.Firmbankingstatus(0),
                        new FirmbankingRequest.FirmbankingAggregateIdentifier(aggregateId)
                );

                // 2. b 계좌에 입금(외부은행에 펌뱅킹 요청)
                FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(
                        new ExternalFirmbankingRequest(
                                command.getFromBankName(),
                                command.getFromBankAccountNumber(),
                                command.getToBankName(),
                                command.getToBankAccountNumber(),
                                command.getMoneyAmount()
                        )
                );

                // Transactional UUID 생성
                UUID randomUUID = UUID.randomUUID();
                firmbankingRequestJpaEntity.setUuid(randomUUID);

                // 3. 결과에 따라서 FirmbankingRequest 정보 Update
                if (firmbankingResult.getResultCode() == 0) {
                    // 성공
                    firmbankingRequestJpaEntity.setFirmbankingStatus(1);
                } else {
                    // 실패
                    firmbankingRequestJpaEntity.setFirmbankingStatus(2);
                }

            }
        });


        return null;

    }
}