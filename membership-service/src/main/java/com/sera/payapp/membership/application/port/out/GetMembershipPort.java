package com.sera.payapp.membership.application.port.out;

import com.sera.payapp.membership.adapter.out.persistence.MembershipJpaEntity;
import com.sera.payapp.membership.domain.Membership;

public interface GetMembershipPort {
    MembershipJpaEntity getMembership(Membership.MembershipId membershipId);
}
