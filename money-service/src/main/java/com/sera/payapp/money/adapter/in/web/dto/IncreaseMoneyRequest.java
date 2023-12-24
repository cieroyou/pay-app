package com.sera.payapp.money.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IncreaseMoneyRequest {
    private String targetMembershipId;
    private int amount;


}
