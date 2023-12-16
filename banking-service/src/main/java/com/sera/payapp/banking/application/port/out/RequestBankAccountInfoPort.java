package com.sera.payapp.banking.application.port.out;

import com.sera.payapp.banking.adapter.out.external.bank.BankAccountInfo;

/**
 * 외부 은행에 계좌정보 요청
 */
public interface RequestBankAccountInfoPort {
    BankAccountInfo getBankAccountInfo(String bankName, String bankAccountNumber);
}
