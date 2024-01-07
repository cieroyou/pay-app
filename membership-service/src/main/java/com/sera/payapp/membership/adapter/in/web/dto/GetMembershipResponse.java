package com.sera.payapp.membership.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.payapp.membership.domain.Membership;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class GetMembershipResponse {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    @Schema(name = "isValid")
    @JsonProperty(value = "isValid")
    @Getter(AccessLevel.NONE)
    private final boolean isValid;
    @Schema(name = "isCorp")
    @Getter(AccessLevel.NONE)
    @JsonProperty(value = "isCorp")
    private final boolean isCorp;

    public GetMembershipResponse(Membership membership) {
        this.membershipId = membership.getMembershipId();
        this.name = membership.getName();
        this.email = membership.getEmail();
        this.address = membership.getAddress();
        this.isValid = membership.isValid();
        this.isCorp = membership.isCorp();
    }
}
