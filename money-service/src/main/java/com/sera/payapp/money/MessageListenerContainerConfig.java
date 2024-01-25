package com.sera.payapp.money;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.payapp.common.CountDownLatchManager;
import com.sera.payapp.common.LoggingProducer;
import com.sera.payapp.money.adapter.in.kafka.RechargingMoneyResultConsumer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MessageListenerContainerConfig {

    @Value("${kafka.clusters.bootstrapservers}")
    private String bootstrapServers;
    @Value("${task.result.topic}")
    private String topic;

    private final ObjectMapper objectMapper;
    private final LoggingProducer loggingProducer;
    private final CountDownLatchManager countDownLatchManager;

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(topic);
        containerProperties.setAckMode(ContainerProperties.AckMode.RECORD);
        containerProperties.setGroupId("task-result-group");
        containerProperties.setMessageListener(new RechargingMoneyResultConsumer(objectMapper, loggingProducer, countDownLatchManager));
        return new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties);
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(props());
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
