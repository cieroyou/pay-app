package com.sera.payapp.money.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.CountDownLatchManager;
import com.sera.payapp.common.LoggingProducer;
import com.sera.payapp.common.RechargingMoneyTask;
import com.sera.payapp.common.SubTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Task 의 결과를 consume 한 후, taskId 에 해당하는 CountDownLatch 를 countDown 한다.
 * IncreaseMoneyAsyncService 에서는 CountDownLatch 를 await 하고 있고, countDown이 트리거 되면 await 이 풀려 다름 비즈니스 로직을 진행한다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RechargingMoneyResultConsumer implements MessageListener<String, String> {
    private final ObjectMapper objectMapper;
    private final LoggingProducer loggingProducer;
    private final CountDownLatchManager countDownLatchManager;

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("Received message, key: {}, value: {}", data.key(), data.value());
        RechargingMoneyTask task;
        try {
            task = objectMapper.readValue(data.value(), RechargingMoneyTask.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<SubTask> subTaskList = task.getSubTasks();

        boolean taskResult = true;
        // validation membership
        // validation banking
        for (SubTask subTask : subTaskList) {
            // 한번만 실패해도 실패한 task 로 간주. 모두 성공해야만 성공한 task 로 간주
            if (subTask.getStatus().equals("fail")) {
                taskResult = false;
                break;
            }
        }

        // 모두 정상적으로 성공했다면
        String taskId = task.getTaskId();
        if (taskResult) {
            this.loggingProducer.sendMessage(taskId, "task success");
            this.countDownLatchManager.setDataForKey(taskId, "success");
        } else {
            this.loggingProducer.sendMessage(taskId, "task failed");
            this.countDownLatchManager.setDataForKey(taskId, "failed");
        }

        this.countDownLatchManager.getCountDownLatch(taskId).countDown();

    }
}
