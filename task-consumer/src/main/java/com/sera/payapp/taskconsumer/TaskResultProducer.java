package com.sera.payapp.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.RechargingMoneyTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TaskResultProducer {

    @Value("${task.result.topic}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // task.status 가 ready 이면 안됨. status 는 success, fail 만 가능
    public void sendTaskResult(RechargingMoneyTask task) {
        String jsonStringToProduce;
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object: " + e.getMessage());
            throw new RuntimeException(e);
        }

        String key = task.getTaskId();
        kafkaTemplate.send(topic, key, jsonStringToProduce).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message: " + ex.getMessage());
            } else {
                log.info("Message sent successfully");
            }
        });
    }
}
