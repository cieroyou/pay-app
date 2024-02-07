package com.sera.payapp.banking.adapter.in.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFirmbankingRequest {

    private String firmbankingAggregateIdentifier;
    private int status;

}
