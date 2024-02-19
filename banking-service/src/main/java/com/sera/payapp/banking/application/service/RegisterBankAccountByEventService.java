package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.axon.command.RegisteredBankAccountCreatedCommand;
import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountJpaEntity;
import com.sera.payapp.banking.adapter.out.persistence.registered_bank_account.RegisteredBankAccountMapper;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountCommand;
import com.sera.payapp.banking.application.port.in.RegisterBankAccountUseCase;
import com.sera.payapp.banking.application.port.out.GetMembershipPort;
import com.sera.payapp.banking.application.port.out.RegisterBankAccountPort;
import com.sera.payapp.banking.application.port.out.RequestBankAccountInfoPort;
import com.sera.payapp.banking.domain.RegisteredBankAccount;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@UseCase
@Slf4j
@Primary
public class RegisterBankAccountByEventService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerMemberAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetMembershipPort getMembershipPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final CommandGateway commandGateway;


    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        commandGateway.send(new RegisteredBankAccountCreatedCommand(
                command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()
        )).whenComplete((result, throwable) -> {
            if (throwable != null) {
                throw new RuntimeException(throwable);
            } else {
                String aggregateId = result.toString();
                log.info("Register Bank Account request success, AggregateId: {}", aggregateId);
                RegisteredBankAccountJpaEntity jpaEntity = registerMemberAccountPort.createBankAccount(
                        new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                        new RegisteredBankAccount.BankName(command.getBankName()),
                        new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                        new RegisteredBankAccount.LinkedStatusIsValid(command.getIsValid()),
                        new RegisteredBankAccount.AggregateIdentifier(aggregateId)
                );
                log.info("create Bank account, RegisteredBankAccountJpaEntity: {}", jpaEntity);
            }

        });
        return null;
    }
}
