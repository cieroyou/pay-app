package com.sera.payapp.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingProducer {
    @Value("${logging.topic}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, String value) {
        kafkaTemplate.send(topic, key, value).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message: " + ex.getMessage());
            } else {
                log.info("Message sent successfully");
            }
        });

    }
}
