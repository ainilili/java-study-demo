package org.nico.sort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SleepSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(array.length, array.length, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());    
        final AtomicInteger atomic = new AtomicInteger(0);
        final CountDownLatch cdl = new CountDownLatch(array.length);
        int[] newArray = new int[array.length];
        for(int index = 0; index < array.length; index ++) {
            final int in = index;
            tpe.execute(() -> {
                try {
                    Thread.sleep(array[in]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newArray[atomic.getAndIncrement()] = array[in];
                cdl.countDown();
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print(newArray);
        return newArray;
    }

}
