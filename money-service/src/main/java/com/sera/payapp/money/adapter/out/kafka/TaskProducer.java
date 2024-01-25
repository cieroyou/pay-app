package com.sera.payapp.money.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.RechargingMoneyTask;
import com.sera.payapp.money.application.port.out.dto.SendRechargingMoneyTaskPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TaskProducer implements SendRechargingMoneyTaskPort {

    @Value("${task.topic}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(String key, RechargingMoneyTask task) {
        String jsonStringToProduce;
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object: " + e.getMessage());
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(topic, key, jsonStringToProduce).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message: " + ex.getMessage());
            } else {
                log.info("Message sent successfully");
            }
        });
    }

    @Override
    public void sendRechargingMoneyTaskPort(RechargingMoneyTask task) {
        sendMessage(task.getTaskId(), task);
    }
}
