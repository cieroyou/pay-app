package com.sera.payapp.remittance.application.port.out;

import com.sera.payapp.remittance.application.port.out.dto.BankingInfo;

public interface BankingPort {
    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);
}
