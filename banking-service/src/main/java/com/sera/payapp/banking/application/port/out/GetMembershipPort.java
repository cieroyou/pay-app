package com.sera.payapp.banking.application.port.out;

import com.sera.payapp.banking.application.port.out.dto.MembershipStatus;

public interface GetMembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
