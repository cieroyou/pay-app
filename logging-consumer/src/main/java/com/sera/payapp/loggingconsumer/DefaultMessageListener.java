package com.sera.payapp.loggingconsumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Slf4j
public class DefaultMessageListener
        implements MessageListener<String, String> {
    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("Received message.key: {}, message.value: {}", data.key(), data.value());
    }
}
