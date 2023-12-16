package com.sera.payapp.banking.application.port.in;

import com.sera.payapp.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);
}
