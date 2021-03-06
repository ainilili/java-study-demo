package org.nico.middleware.kafka;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {

    private final static Logger logger = LoggerFactory.getLogger(Consumer.class); 
    
    private final KafkaConsumer<Integer, String> consumer;
    
    private final String topic;

    public Consumer(String topic) {
        Properties props = new Properties();
        try {
            props.load(Producer.class.getResourceAsStream("/kafka-consumer.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        
        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    public void receive(){
        try {
            consumer.subscribe(Collections.singletonList(this.topic));
            while(true){
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofDays(2));
                for (ConsumerRecord<Integer, String> record : records) {
                    logger.info("Received message: ({}, {}) at partition {} offset {}", record.key(), record.value(), record.partition(), record.offset());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
