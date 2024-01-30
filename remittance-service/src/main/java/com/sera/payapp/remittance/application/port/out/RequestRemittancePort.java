package com.sera.payapp.remittance.application.port.out;

import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;

public interface RequestRemittancePort {

    // TODO: port 는 application layer 에 있으므로 adapter 를 의존하지 않는 것이 좋음. RemittanceRequest 엔티티를 리턴하도록 나중에 수정이 필요
    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);

    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
