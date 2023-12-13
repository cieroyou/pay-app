package com.sera.payapp.membership.adapter.in.web;


import com.sera.payapp.membership.adapter.in.web.dto.RegisterMembershipRequest;
import com.sera.payapp.membership.application.port.in.RegisterMembershipCommand;
import com.sera.payapp.membership.application.port.in.RegistermembershipUseCase;
import com.sera.payapp.membership.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegistermembershipUseCase registermembershipUseCase;

    @PostMapping
    void resisterMembership(@RequestBody RegisterMembershipRequest request) {

        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        registermembershipUseCase.registerMembership(command);

    }

}
