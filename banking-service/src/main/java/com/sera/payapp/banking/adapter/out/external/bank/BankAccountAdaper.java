package com.sera.payapp.banking.adapter.out.external.bank;

import com.sera.payapp.banking.application.port.out.RequestBankAccountInfoPort;
import com.sera.payapp.banking.application.port.out.RequestExternalFirmbankingPort;
import com.sera.payapp.common.ExternalSystemAdapter;

import java.util.Random;

@ExternalSystemAdapter
public class BankAccountAdaper implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {
    @Override
    public BankAccountInfo getBankAccountInfo(String bankName, String bankAccountNumber) {
        return new BankAccountInfo(bankName, bankAccountNumber, new Random().nextBoolean());
    }

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest externalFirmbankingRequest) {
        // TODO: 외부 은행에 펌뱅킹 Http 요청
        return new FirmbankingResult(new Random().nextInt(2));
    }
}
