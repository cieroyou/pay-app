package com.sera.payapp.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoggingProducer {
    @Value("${logging.topic}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, String value) {
        kafkaTemplate.send(topic, key, value);
    }
}
