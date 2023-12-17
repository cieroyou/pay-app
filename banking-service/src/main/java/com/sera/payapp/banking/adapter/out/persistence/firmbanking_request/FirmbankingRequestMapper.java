package com.sera.payapp.banking.adapter.out.persistence.firmbanking_request;

import com.sera.payapp.banking.domain.FirmbankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmbankingRequestMapper {
    public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity, UUID uuid) {
        return FirmbankingRequest.generateFirmbankingRequest(
                new FirmbankingRequest.FirmbankingRequestId(firmbankingRequestJpaEntity.getRequestFirmbankingId().toString()),
                new FirmbankingRequest.FromBankName(firmbankingRequestJpaEntity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(firmbankingRequestJpaEntity.getFromBankBankAccountNumber()),
                new FirmbankingRequest.ToBankName(firmbankingRequestJpaEntity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(firmbankingRequestJpaEntity.getToBankBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(firmbankingRequestJpaEntity.getMoneyAmount()),
                new FirmbankingRequest.Firmbankingstatus(firmbankingRequestJpaEntity.getFirmbankingStatus()
                ), uuid);
    }
}
