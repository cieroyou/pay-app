package com.sera.payapp.remittance.application.port.out;

import com.sera.payapp.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.sera.payapp.remittance.application.port.in.dto.GetRemittanceQuery;

import java.util.List;

public interface GetRemittanceRequestPort {
    List<RemittanceRequestJpaEntity> getRemittanceRequest(GetRemittanceQuery getRemittanceQuery);
}
