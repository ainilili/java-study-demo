package org.nico.dbpool.nico;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class NicoConnectionPool {

    private NicoConnection[] pools;

    private AtomicInteger poolCursor;

    private int maxSize;

    private List<Integer> leisureIds;

    final Lock addLock = new ReentrantLock();
    final Lock leisureLock = new ReentrantLock();
    
    final Condition leisureCondition = leisureLock.newCondition();  

    public NicoConnectionPool(int max) {
        maxSize = max;
        pools = new NicoConnection[max];
        leisureIds = new ArrayList<Integer>();
        poolCursor = new AtomicInteger(-1);
    }

    public Lock getLeisureLock() {
        return leisureLock;
    }

    public boolean isFull() {
        return poolCursor.get() == maxSize - 1;
    }

    public boolean hasLeisure() {
        return leisureIds.size() > 0;
    }

    public Condition getLeisureCondition() {
        return leisureCondition;
    }

    public NicoConnection getConnection(long wait) {
        try {
            leisureLock.lock();
            if(! leisureIds.isEmpty()) {
                Integer index = leisureIds.get(0);
                if(index != null) {
                    NicoConnection nc = pools[index];
                    if(nc.isClosed()) {
                        nc.setUsed(true);
                        leisureIds.remove(nc.getId());
                        return nc;
                    }
                }
            }
            long time = leisureCondition.awaitNanos(wait);
            if(time > 0) {
                return getConnection(time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            leisureLock.unlock();
        }
        throw new RuntimeException("获取连接超时");
    }

    public NicoConnection addUsedConnection(NicoConnection nc) {
        addLock.lock();
        Integer id = poolCursor.incrementAndGet();
        pools[id] = nc;
        nc.setUsed(true);
        nc.setId(id);
        addLock.unlock();
        return nc;
    }
    
    public NicoConnection putUsedConnection(NicoConnection nc, Integer id) {
        addLock.lock();
        pools[id] = nc;
        nc.setUsed(true);
        nc.setId(id);
        leisureIds.add(id);
        addLock.unlock();
        return nc;
    }

    public NicoConnection addUnUsedConnection(NicoConnection nc) {
        addLock.lock();
        Integer id = poolCursor.incrementAndGet();
        pools[id] = nc;
        nc.setUsed(false);
        nc.setId(id);
        leisureIds.add(id);
        addLock.unlock();
        return nc;
    }
    
    public void addToLeisureIds(NicoConnection nc) {
        try {
            leisureLock.lock();
            leisureIds.add(nc.getId());
            leisureCondition.signal();   
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            leisureLock.unlock();
        }
        
    }

    public NicoConnection[] getPools() {
        return pools;
    }

    public void setPools(NicoConnection[] pools) {
        this.pools = pools;
    }
    
   
}
