package com.sera.payapp.membership.application.service;

import com.sera.payapp.common.UseCase;
import com.sera.payapp.membership.adapter.out.persistence.MembershipJpaEntity;
import com.sera.payapp.membership.adapter.out.persistence.MembershipMapper;
import com.sera.payapp.membership.application.port.in.GetMembershipQuery;
import com.sera.payapp.membership.application.port.in.GetMembershipUseCase;
import com.sera.payapp.membership.application.port.out.GetMembershipPort;
import com.sera.payapp.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@UseCase
public class GetMembershipService implements GetMembershipUseCase {

    private final GetMembershipPort getMembershipPort;
    private final MembershipMapper membershipMapper;


    @Override
    public Membership getMembership(GetMembershipQuery query) {
        MembershipJpaEntity jpaEntity = getMembershipPort.getMembership(
                new Membership.MembershipId(query.getMembershipId()));
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
