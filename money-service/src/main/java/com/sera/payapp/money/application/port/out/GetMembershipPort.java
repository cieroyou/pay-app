package com.sera.payapp.money.application.port.out;


import com.sera.payapp.money.application.port.out.dto.MembershipStatus;

public interface GetMembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
