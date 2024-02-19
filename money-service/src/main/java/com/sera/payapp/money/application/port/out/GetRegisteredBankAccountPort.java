package com.sera.payapp.money.application.port.out;

import com.sera.payapp.money.application.port.out.dto.RegisteredBankAccountWithAggregateIdentifier;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountWithAggregateIdentifier getRegisteredBankAccount(String membershipId);
}
