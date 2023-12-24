package com.sera.payapp.money.adapter.out.persistence;

import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.money.application.port.out.IncreaseMoneyChangingRequestPort;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestAdapter implements IncreaseMoneyChangingRequestPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

    @Override
    public MoneyChangingRequestJpaEntity increaseMoney(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.LinkedStatusIsValid linkedStatusIsValid,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid
    ) {
        return moneyChangingRequestRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        moneyChangingType.getMoneyChangingType().getValue(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        linkedStatusIsValid.isLinkedStatusIsValid(),
                        moneyChangingStatus.getMoneyChangingStatus().getValue(),
                        UUID.randomUUID(),
                        new Timestamp(System.currentTimeMillis())
                )
        );
    }


}
