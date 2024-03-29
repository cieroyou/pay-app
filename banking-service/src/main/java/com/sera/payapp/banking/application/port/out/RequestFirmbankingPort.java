package com.sera.payapp.banking.application.port.out;

import com.sera.payapp.banking.adapter.out.persistence.firmbanking_request.FirmbankingRequestJpaEntity;
import com.sera.payapp.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingPort {

    FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.Firmbankingstatus firmbankingStatus,
            FirmbankingRequest.FirmbankingAggregateIdentifier aggregateIdentifier
    );

    FirmbankingRequestJpaEntity modifyFirmbankingRequest(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity);

    FirmbankingRequestJpaEntity getFirmbankingRequest(
            FirmbankingRequest.FirmbankingAggregateIdentifier firmbankingAggregateIdentifier
    );
}
