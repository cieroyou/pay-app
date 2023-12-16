package com.sera.payapp.banking.adapter.out.external.bank;

import com.sera.payapp.banking.application.port.out.RequestBankAccountInfoPort;
import com.sera.payapp.common.ExternalSystemAdapter;

import java.util.Random;

@ExternalSystemAdapter
public class BankAccountAdaper implements RequestBankAccountInfoPort {
    @Override
    public BankAccountInfo getBankAccountInfo(String bankName, String bankAccountNumber) {
        return new BankAccountInfo(bankName, bankAccountNumber, new Random().nextBoolean());
    }
}
