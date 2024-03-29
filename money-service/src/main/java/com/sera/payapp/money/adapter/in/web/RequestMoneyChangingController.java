package com.sera.payapp.money.adapter.in.web;

import com.sera.payapp.common.WebAdapter;
import com.sera.payapp.money.adapter.in.web.dto.IncreaseMoneyRequest;
import com.sera.payapp.money.adapter.in.web.dto.MoneyChangingResultDetail;
import com.sera.payapp.money.application.port.in.IncreaseMoneyCommand;
import com.sera.payapp.money.application.port.in.IncreaseMoneyUseCase;
import com.sera.payapp.money.domain.MoneyChangingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 돈 증액/감액 요청을 처리하는 컨트롤러
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyUseCase increaseMoneyUseCase;

    @PostMapping(path = "/money/increase")
    ResponseEntity<MoneyChangingResultDetail> increaseMoneyRequest(@Valid @RequestBody IncreaseMoneyRequest request) {
        MoneyChangingRequest moneyChangingRequest = increaseMoneyUseCase.increaseMoney(new IncreaseMoneyCommand(
                request.getTargetMembershipId(),
                request.getAmount()
        ));
//        var result = MoneyChangingResultDetail.fromMoneyChangingRequest(moneyChangingRequest);
//        return ResponseEntity.ok(MoneyChangingResultDetail.fromMoneyChangingRequest(moneyChangingRequest));
        return ResponseEntity.ok(null);
    }

}
