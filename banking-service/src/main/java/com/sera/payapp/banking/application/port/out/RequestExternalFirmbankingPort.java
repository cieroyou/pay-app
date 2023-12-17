package com.sera.payapp.banking.application.port.out;

import com.sera.payapp.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.sera.payapp.banking.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    /**
     * 외부 은행에 펌뱅킹 요청
     *
     * @return 펌뱅킹 요청 결과(0: 성공, 1: 실패)
     */
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest externalFirmbankingRequest);
}
