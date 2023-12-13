package com.sera.payapp.membership.application.port.in;

import com.sera.payapp.membership.domain.Membership;

public interface RegistermembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
