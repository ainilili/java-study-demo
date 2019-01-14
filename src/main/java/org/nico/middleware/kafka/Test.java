package org.nico.middleware.kafka;

public class Test {

    static String topic = "test";
    
    public static void main(String[] args) {
        
        Producer producer = new Producer(topic, args);
        producer.send("hello");
        
        Consumer consumer = new Consumer(topic);
        consumer.receive();
        
    }
}
