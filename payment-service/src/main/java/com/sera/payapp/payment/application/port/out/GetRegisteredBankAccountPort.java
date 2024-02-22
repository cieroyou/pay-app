package com.sera.payapp.payment.application.port.out;


import com.sera.payapp.payment.application.port.out.dto.RegisteredBankAccountWithAggregateIdentifier;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountWithAggregateIdentifier getRegisteredBankAccount(String membershipId);
}
