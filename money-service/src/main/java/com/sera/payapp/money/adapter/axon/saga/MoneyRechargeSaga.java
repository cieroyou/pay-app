package com.sera.payapp.money.adapter.axon.saga;

import com.sera.payapp.common.event.CheckRegisteredBankAccountCommand;
import com.sera.payapp.common.event.CheckedRegisteredBankAccountEvent;
import com.sera.payapp.money.adapter.axon.event.RechargingMoneyRequestCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


/**
 * 계좌가 유효한지 체크 - CheckRegisterBankAccountCommand -> Check Bank Account
 */
@Saga
@Slf4j
@NoArgsConstructor
public class MoneyRechargeSaga {
    private static final String RECHARGING_REQUEST_ID_ASSOCIATION = "rechargingRequestId";
    private static final String CHECK_REGISTERED_BANK_ACCOUNT_ID_ASSOCIATION = "checkRegisteredBankAccountId";

    @Autowired
    private transient CommandGateway commandGateway;

    // TODO: RequiredArgsConstructor 를 사용하여 commandGateway 를 주입받도록 하기
    public MoneyRechargeSaga(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    // 부하가 많은 상황에서는 여러개의 Saga 가 존재할 것이며, 그것을 메모리 안에서 구분하기 위해 사용되는 구분자가 associationProperty 이다.
    @StartSaga
    @SagaEventHandler(associationProperty = RECHARGING_REQUEST_ID_ASSOCIATION)
    public void handle(RechargingMoneyRequestCreatedEvent event) {
        log.info("RechargingRequestCreatedEvent Start saga, event: {}", event.toString());
        // 이벤트 핸들링을 할 때, 해당 이벤트를 발생시킨 Aggregate 의 ID 를 associationProperty 로 지정하여, 해당 Saga 를 구분할 수 있도록 한다.
        // 뱅킹에 등록된 계좌가 정상적인지 확인
        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith(CHECK_REGISTERED_BANK_ACCOUNT_ID_ASSOCIATION, checkRegisteredBankAccountId);

        commandGateway.send(new CheckRegisteredBankAccountCommand(
                event.getRegisteredBankAccountAggregateIdentifier(),
                event.getRechargingRequestId(),
                event.getMembershipId(),
                checkRegisteredBankAccountId,
                event.getBankName(),
                event.getBankAccountNumber(),
                event.getAmount()
        )).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("CheckRegisteredBankAccountCommand failed, throwable: " + throwable);
                throw new RuntimeException(throwable);
            }
            log.info("CheckRegisteredBankAccountCommand success, aggregateId: {}", result.toString());
        });
        // CheckRegisterBankAccountCommand -> Check Bank Account
        // axon server -> banking-service -> Command

    }

    @SagaEventHandler(associationProperty = CHECK_REGISTERED_BANK_ACCOUNT_ID_ASSOCIATION)
    public void handle(CheckedRegisteredBankAccountEvent event) {
        log.info("CheckedRegisteredBankAccountEvent saga: " + event.toString());
    }
}
