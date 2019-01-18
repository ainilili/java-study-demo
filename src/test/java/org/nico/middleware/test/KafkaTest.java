package org.nico.middleware.test;

import java.util.Scanner;

import org.junit.Test;
import org.nico.middleware.kafka.Consumer;
import org.nico.middleware.kafka.Producer;

public class KafkaTest {

    static String topic = "test";

    @Test
    public void test() {

        new Thread(() ->  {
            Scanner in = null;
            try {
                in = new Scanner(System.in);
                Producer producer = new Producer(topic, null);
                while(in.hasNext()) {
                    producer.send(in.next());
                }
            } finally {
                if(null != in) in.close();
            }
        }).start();
        
        
        new Thread(() ->  {
            Consumer consumer = new Consumer(topic);
            consumer.receive();
        }).start();

    }
}
