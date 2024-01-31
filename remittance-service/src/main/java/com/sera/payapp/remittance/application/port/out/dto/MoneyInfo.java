package com.sera.payapp.remittance.application.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoneyInfo {
    private String membershipId;
    private int balance;
}
