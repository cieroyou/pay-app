package com.sera.payapp.taskconsumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.RechargingMoneyTask;
import com.sera.payapp.common.SubTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

import java.util.List;

/**
 * 발행된 RechargingMoneyTask 메세지를 받아서 처리하는 Consumer
 * 태스트에 해당하는 작업(internal Request) 를 수행 후 받은 작업을 완료했다는 메세지(set task.status) 를 발행한다.
 */
@Slf4j
@RequiredArgsConstructor
public class TaskConsumer
        implements MessageListener<String, String> {

    private final ObjectMapper objectMapper;
    private final TaskResultProducer taskResultProducer;

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
//        Received message.key: 886e9735-de87-4c6b-b085-495c5a4ab0ed,
//        message.value: {"taskId":"886e9735-de87-4c6b-b085-495c5a4ab0ed",
//        "taskName":"Increase Money Task / 머니 충전 Task",
//        "membershipId":null,
//        "subTasks":[
//        {"membershipId":null,
//        "subTaskName":"validMemberTask: 멤버쉽 유효성 검사",
//        "taskType":"membership","status":"ready"},
//        {"membershipId":null,
//        "subTaskName":"validBankAccountTask: 뱅킹계좌 유효성 검사",
//        "taskType":"banking",
//        "status":"ready"}
//        ],
//        "toBankName":"우리은행","toBankAccountNumber":"123-456-789","moneyAmount":0}
        RechargingMoneyTask task;
        try {
            task = objectMapper.readValue(data.value(), RechargingMoneyTask.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<SubTask> subTasks = task.getSubTasks();

        boolean taskResult = true;
        for (SubTask subTask : subTasks) {
            // TODO: validation membership
            // TODO: validation banking
            // TODO: 실제로 external port, adapter 를 통해 validation 을 수행하고 결과를 받아온다.
            subTask.setStatus("success");
        }
        if (taskResult) {
            this.taskResultProducer.sendTaskResult(task);
        }
    }
}
