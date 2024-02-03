package com.sera.payapp.money.application.service;

import com.sera.payapp.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.sera.payapp.money.adapter.in.web.dto.CreateMemberMoneyCommand;
import com.sera.payapp.money.application.port.in.CreateMemberMoneyUseCase;
import com.sera.payapp.money.application.port.out.GetMembershipPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateMemberMoneyService implements CreateMemberMoneyUseCase {

//    private final EventStore eventStore;
//    private final CommandGateway commandGateway;

//    public CreateMemberMoneyService(CommandGateway commandGateway) {
//        this.commandGateway = commandGateway;
//    }

    /**
     * Member 지갑 생성(초기 생성)
     */
    @Override
    public void createMemberMoney(CreateMemberMoneyCommand command) {
        // EventSourcing 사용 시작 구간
        MemberMoneyCreatedCommand axonCommand = new MemberMoneyCreatedCommand(command.getMembershipId());
        // CommandHandler가 EventQueue에 MemberMoneyCreatedEvent를 넣는 작업으로,
        // MemberMoneyAggregate의 생성자가 호출되고, MemberMoneyCreatedEvent를 받아서
        // MemberMoneyAggregate의 상태를 변경한다.
//        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
//                    // 이벤트 소싱이 모두 완료 되면 전달받음
//                    if (throwable != null) {
//                        log.error("MemberMoneyCreatedCommand 전달 실패", throwable);
//                        throw new RuntimeException(throwable);
//                    } else {
//                        log.info("MemberMoneyCreatedCommand 전달 성공, 결과: {}", result);
//                    }
//                }
//        );

    }
}
