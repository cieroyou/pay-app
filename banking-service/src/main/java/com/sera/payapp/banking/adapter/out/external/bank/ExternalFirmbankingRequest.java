package com.sera.payapp.banking.adapter.out.external.bank;

public record ExternalFirmbankingRequest(

        String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber,
        int moneyAmount) {

}
