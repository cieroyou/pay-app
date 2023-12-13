package com.sera.payapp.membership.application.service;

import com.sera.payapp.membership.adapter.out.persistence.MembershipJpaEntity;
import com.sera.payapp.membership.adapter.out.persistence.MembershipMapper;
import com.sera.payapp.membership.application.port.in.RegisterMembershipCommand;
import com.sera.payapp.membership.application.port.in.RegistermembershipUseCase;
import com.sera.payapp.membership.application.port.out.RegisterMembershipPort;
import com.sera.payapp.membership.common.UseCase;
import com.sera.payapp.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@UseCase
public class RegisterMembershipService implements RegistermembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipJpaEntity jpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
