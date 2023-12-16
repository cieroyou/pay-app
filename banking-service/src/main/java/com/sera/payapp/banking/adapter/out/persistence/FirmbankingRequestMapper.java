package com.sera.payapp.banking.adapter.out.persistence;

import com.sera.payapp.banking.domain.FirmbankingRequest;
import org.springframework.stereotype.Component;

@Component
public class FirmbankingRequestMapper {
    public static FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity) {
        return FirmbankingRequest.generateFirmbankingRequest(
                new FirmbankingRequest.FirmbankingRequestId(firmbankingRequestJpaEntity.getRequestFirmbankingId().toString()),
                new FirmbankingRequest.FromBankName(firmbankingRequestJpaEntity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(firmbankingRequestJpaEntity.getFromBankBankAccountNumber()),
                new FirmbankingRequest.ToBankName(firmbankingRequestJpaEntity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(firmbankingRequestJpaEntity.getToBankBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(firmbankingRequestJpaEntity.getMoneyAmount()),
                new FirmbankingRequest.Firmbankingstatus(firmbankingRequestJpaEntity.getFirmbankingStatus(
                )
                ));
    }
}
