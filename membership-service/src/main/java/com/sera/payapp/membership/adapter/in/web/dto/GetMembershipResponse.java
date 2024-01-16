package com.sera.payapp.membership.adapter.in.web.dto;

import com.sera.payapp.membership.domain.Membership;
import lombok.Getter;

@Getter
public class GetMembershipResponse {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final Boolean isValid;
    private final Boolean isCorp;

    public GetMembershipResponse(Membership membership) {
        this.membershipId = membership.getMembershipId();
        this.name = membership.getName();
        this.email = membership.getEmail();
        this.address = membership.getAddress();
        this.isValid = membership.isValid();
        this.isCorp = membership.isCorp();
    }
}
