package org.nico.data.structure.juc;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
 
/**
 *
 * @author nico
 * @email ainililia@163.com
 */
 
public class CountDownLatchDefinedTest {
    
    @Test
    public void test() throws InterruptedException {
        int sum = 0;
        int count = 1000;
        int loop = 1000;
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(100, 100, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
 
        while(count -- > 0){
            AtomicDownLatch as = new AtomicDownLatch(loop);
            for(int i = 0; i < loop; i ++){
                tpe.execute(as::countDown);
            }
            as.await();
            sum ++;
        }
 
        System.out.println(sum);
        tpe.shutdown();
    }
    
}

