package org.nico.middleware.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

    private final KafkaProducer<String, String> producer;
    
    private final String topic;

    public Producer(String topic, String[] args){
        Properties props = new Properties();
        try {
            props.load(Producer.class.getResourceAsStream("/kafka-producer.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        producer = new KafkaProducer<>(props);

        this.topic = topic;
    }
    
    public void send(String message) {
        producer.send(new ProducerRecord<String, String>(topic, "hi", message));
        System.out.println("send success：" + message);
    }
    
}
