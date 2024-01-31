package com.sera.payapp.remittance.application.port.out;

import com.sera.payapp.remittance.application.port.out.dto.BankingInfo;

public interface BankingPort {
    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);

    /**
     * 특정 은행 계좌에 송금요청
     */
    boolean requestFirmbanking(String toBankName, String toBankAccountNumber);
}
