package com.sera.payapp.remittance.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestMapper;
import com.sera.payapp.remittance.application.port.in.GetRemittanceHistoryUseCase;
import com.sera.payapp.remittance.application.port.in.dto.GetRemittanceQuery;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;
import com.sera.payapp.remittance.application.port.out.GetRemittanceRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class GetRemittanceHistoryService implements GetRemittanceHistoryUseCase {

    private final GetRemittanceRequestPort getRemittanceRequestPort;
    private final RemittanceRequestMapper remittanceRequestMapper;

    @Override
    public List<RequestRemittanceInfo> getRemittanceHistory(GetRemittanceQuery getRemittanceQuery) {
        List<RemittanceRequestJpaEntity> remittanceRequests =
                getRemittanceRequestPort.getRemittanceRequest(getRemittanceQuery);
        return remittanceRequests.stream()
                .map(remittanceRequestMapper::mapToDomainEntity)
                .map(RequestRemittanceInfo::fromEntity)
                .toList();
    }
}
