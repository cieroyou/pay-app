package com.sera.payapp.banking.application.service;

import com.sera.payapp.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.sera.payapp.banking.adapter.out.external.bank.FirmbankingResult;
import com.sera.payapp.banking.adapter.out.persistence.firmbanking_request.FirmbankingRequestJpaEntity;
import com.sera.payapp.banking.adapter.out.persistence.firmbanking_request.FirmbankingRequestMapper;
import com.sera.payapp.banking.application.port.in.RequestFirmbankingCommand;
import com.sera.payapp.banking.application.port.in.RequestFirmbankingUseCase;
import com.sera.payapp.banking.application.port.out.RequestExternalFirmbankingPort;
import com.sera.payapp.banking.application.port.out.RequestFirmbankingPort;
import com.sera.payapp.banking.domain.FirmbankingRequest;
import com.sera.payapp.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@UseCase
public class RequestFirmbankingService implements RequestFirmbankingUseCase {

    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final RequestFirmbankingPort requestFirmbankingPort;
    private final FirmbankingRequestMapper firmbankingRequestMapper;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        // a -> b 계좌로 송금요청

        // 1. firmbankingRequest 생성
        FirmbankingRequestJpaEntity firmbankingRequestJpaEntity = requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.Firmbankingstatus(0),
                null
        );
        // 2. b 계좌에 입금(외부은행에 펌뱅킹 요청)
        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(
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
        if (result.getResultCode() == 0) {
            // 성공
            firmbankingRequestJpaEntity.setFirmbankingStatus(1);
        } else {
            // 실패
            firmbankingRequestJpaEntity.setFirmbankingStatus(2);
        }

        // 4. firmbankingRequest 반환
        return firmbankingRequestMapper.mapToDomainEntity(requestFirmbankingPort.modifyFirmbankingRequest(firmbankingRequestJpaEntity), randomUUID);

    }
}
