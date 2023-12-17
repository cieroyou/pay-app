package com.sera.payapp.banking.application.port.in;


import com.sera.payapp.banking.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountUseCase {
    RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountQuery query);
}
