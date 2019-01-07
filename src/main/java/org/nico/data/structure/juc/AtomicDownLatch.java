package org.nico.data.structure.juc;
import java.util.concurrent.atomic.AtomicInteger;
 
/**
 *
 * @author nico
 * @email ainililia@163.com
 */
public class AtomicDownLatch {
 
    private volatile int state;
 
    private AtomicInteger count;
 
    private Object lock = new Object();
 
    public AtomicDownLatch(int count){
        this.count = new AtomicInteger(count);
    }
 
    public void countDown(){
        count.decrementAndGet();
        release();
    }
 
    public boolean release(){
        int s = count.get();
        if(s == 0){
            synchronized (lock) {
                lock.notifyAll();
            }
            return true;
        }else{
            return false;
        }
    }
 
    public void await() throws InterruptedException{
        synchronized (lock) {
            if(state == 0){
                state = 1;
                if(! release()){
                    lock.wait();
                }
            }
        }
    }
}