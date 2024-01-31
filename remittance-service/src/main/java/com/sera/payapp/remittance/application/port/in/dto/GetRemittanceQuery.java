package com.sera.payapp.remittance.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRemittanceQuery {
    String membershipId;
}
