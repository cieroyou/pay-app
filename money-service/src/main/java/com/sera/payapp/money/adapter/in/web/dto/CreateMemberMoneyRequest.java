package com.sera.payapp.money.adapter.in.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberMoneyRequest {
    private String targetMembershipId;
}
