package com.sera.payapp.money.adapter.in.web;

import com.sera.payapp.common.WebAdapter;
import com.sera.payapp.money.adapter.in.web.dto.CreateMemberMoneyRequest;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyCommand;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 특정 고객(membership) 의 지갑 생성 요청
 */
@WebAdapter
@RequiredArgsConstructor
@RestController
public class CreateMemberMoneyController {

    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;

    @PostMapping(path = "/money/create-member-money")
    void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {
        createMemberMoneyUseCase.createMemberMoney(new CreateMemberMoneyCommand(request.getTargetMembershipId()));
    }

}
