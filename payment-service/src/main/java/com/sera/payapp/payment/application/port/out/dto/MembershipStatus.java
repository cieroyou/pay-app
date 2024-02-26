package com.sera.payapp.payment.application.port.out.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembershipStatus {
    private String membershipId;
    private boolean isValid;
}
