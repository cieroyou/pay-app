package com.sera.payapp.membership.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMembershipQuery {
    String membershipId;
}
