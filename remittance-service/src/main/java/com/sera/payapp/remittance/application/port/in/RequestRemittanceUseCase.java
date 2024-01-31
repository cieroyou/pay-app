package com.sera.payapp.remittance.application.port.in;

import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceCommand;
import com.sera.payapp.remittance.application.port.in.dto.RequestRemittanceInfo;

public interface RequestRemittanceUseCase {
    RequestRemittanceInfo requestRemittance(RequestRemittanceCommand command);
}
