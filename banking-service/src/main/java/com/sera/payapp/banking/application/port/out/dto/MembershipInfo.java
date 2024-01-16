package com.sera.payapp.banking.application.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MembershipInfo {
    private String membershipId;
    private String name;
    private String email;
    private String address;
    @JsonProperty(value = "isValid")
    private boolean isValid;
    @JsonProperty(value = "isCorp")
    private boolean isCorp;
}
