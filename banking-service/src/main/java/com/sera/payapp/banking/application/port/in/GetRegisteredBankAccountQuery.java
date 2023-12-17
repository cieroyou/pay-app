package com.sera.payapp.banking.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRegisteredBankAccountQuery {
    String registeredBankAccountId;

}
