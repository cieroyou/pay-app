package com.sera.payapp.banking.adapter.out.persistence.firmbanking_request;

import com.sera.payapp.banking.application.port.out.RequestFirmbankingPort;
import com.sera.payapp.banking.domain.FirmbankingRequest;
import com.sera.payapp.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort {
    private final SpringDataFirmbankingRequestRepository firmbankingRequestRepository;

    @Override
    public FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FirmbankingRequestId firmbankingRequestId,
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.Firmbankingstatus firmbankingStatus) {
        return firmbankingRequestRepository.save(new FirmbankingRequestJpaEntity(
                fromBankName.getFromBankName(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                toBankName.getToBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                moneyAmount.getMoneyAmount(),
                firmbankingStatus.getFirmbankingStatus(),
                UUID.randomUUID()));
    }

    @Override
    public FirmbankingRequestJpaEntity modifyFirmbankingRequest(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity) {
        return firmbankingRequestRepository.save(firmbankingRequestJpaEntity);
    }
}
