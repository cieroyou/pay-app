package com.sera.payapp.banking.adapter.in.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBankAccountRequest {
    private String membershipId;
    // TODO: enum 으로 리팩토링 할 것
    private String bankName;
    private String bankAccountNumber;
}