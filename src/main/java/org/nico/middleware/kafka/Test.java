package org.nico.middleware.kafka;

import java.util.Scanner;

public class Test {

    static String topic = "test";

    public static void main(String[] args) {

        new Thread(() ->  {
            Scanner in = null;
            try {
                in = new Scanner(System.in);
                Producer producer = new Producer(topic, args);
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
