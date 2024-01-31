package com.sera.payapp.remittance.adapter.out.persistance;

import com.sera.payapp.common.PersistenceAdapter;
import com.sera.payapp.remittance.application.port.in.dto.GetRemittanceQuery;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import com.sera.payapp.remittance.application.port.out.GetRemittanceRequestPort;
import com.sera.payapp.remittance.application.port.out.RequestRemittancePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class RemittanceRequestPersistenceAdapter implements RequestRemittancePort, GetRemittanceRequestPort {

    private final SpringDataRemittanceRequestRepository remittanceRequestRepository;

    @Override
    public RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command) {
        return remittanceRequestRepository.save(RemittanceRequestJpaEntity.builder()
                .fromMembershipId(command.getFromMembershipId())
                .toMembershipId(command.getToMembershipId())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .amount(command.getAmount())
                .remittanceType(command.getRemittanceType())
                .build());
    }

    @Override
    public boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity) {
        remittanceRequestRepository.save(entity);
        return true;
    }

    @Override
    public List<RemittanceRequestJpaEntity> getRemittanceRequest(GetRemittanceQuery getRemittanceQuery) {
        return remittanceRequestRepository.findAllByFromMembershipId(getRemittanceQuery.getMembershipId());
    }
}
