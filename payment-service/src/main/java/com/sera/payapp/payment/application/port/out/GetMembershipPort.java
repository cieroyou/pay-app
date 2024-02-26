package com.sera.payapp.payment.application.port.out;


import com.sera.payapp.payment.application.port.out.dto.MembershipStatus;

public interface GetMembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
