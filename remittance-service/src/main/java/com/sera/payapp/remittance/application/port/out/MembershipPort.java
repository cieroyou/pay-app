package com.sera.payapp.remittance.application.port.out;

import com.sera.payapp.remittance.application.port.out.dto.MembershipStatus;

public interface MembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
