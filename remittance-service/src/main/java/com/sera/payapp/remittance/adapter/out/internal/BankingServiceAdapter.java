package com.sera.payapp.remittance.adapter.out.internal;

import com.sera.payapp.common.CommonHttpClient;
import com.sera.payapp.remittance.application.port.out.BankingPort;
import com.sera.payapp.remittance.application.port.out.dto.BankingInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankingServiceAdapter implements BankingPort {
    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.money.url}")
    private String bankingServiceEndpoint;


    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {
        // TODO 뱅킹 정보 가져오기 구현
        return null;
    }

    @Override
    public boolean requestFirmbanking(String toBankName, String toBankAccountNumber) {
        return true;
    }
}
