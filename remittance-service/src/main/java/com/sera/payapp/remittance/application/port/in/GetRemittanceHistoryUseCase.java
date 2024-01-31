package com.sera.payapp.remittance.application.port.in;

import com.sera.payapp.remittance.application.port.in.dto.GetRemittanceQuery;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;

import java.util.List;

public interface GetRemittanceHistoryUseCase {
    List<RequestRemittanceInfo> getRemittanceHistory(GetRemittanceQuery getRemittanceQuery);
}
