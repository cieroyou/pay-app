package com.sera.payapp.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountInfo {
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}
