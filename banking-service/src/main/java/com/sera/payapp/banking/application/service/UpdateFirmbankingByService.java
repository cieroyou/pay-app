package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.axon.command.RequestFirmbankingUpdatedCommand;
import com.sera.payapp.banking.application.port.in.UpdateFirmbankingCommand;
import com.sera.payapp.banking.application.port.in.UpdateFirmbankingUseCase;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateFirmbankingByService implements UpdateFirmbankingUseCase {
    private final CommandGateway commandGateway;

    @Override
    public void updateFirmbanking(UpdateFirmbankingCommand command) {
        commandGateway.send(new RequestFirmbankingUpdatedCommand(command.getFirmbankingAggregateIdentifier(), command.getStatus()))
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        log.error("Error while updating firmbanking", throwable);
                    } else {
                        log.info("Firmbanking updated successfully, id: {}", result.toString());
                    }
                });

    }
}
