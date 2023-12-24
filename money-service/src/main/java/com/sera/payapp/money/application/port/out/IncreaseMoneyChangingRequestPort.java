package com.sera.payapp.money.application.port.out;

import com.sera.payapp.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.sera.payapp.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyChangingRequestPort {
    MoneyChangingRequestJpaEntity increaseMoney(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.LinkedStatusIsValid linkedStatusIsValid,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid
    );

}
