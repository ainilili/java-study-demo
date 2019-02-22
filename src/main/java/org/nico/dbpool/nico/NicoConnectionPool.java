package org.nico.dbpool.nico;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class NicoConnectionPool {

    private NicoConnection[] pools;

    private AtomicInteger poolCursor;

    private int maxSize;

    private List<Integer> leisureIds;

    private Lock addLock;

    private Lock leisureIndicesLock;

    private Condition getConnectionCondition;

    private Queue<NicoConnection> requestQueue;

    public NicoConnectionPool(int max) {
        maxSize = max;
        pools = new NicoConnection[max];
        leisureIds = new ArrayList<Integer>();
        poolCursor = new AtomicInteger(0);
        leisureIndicesLock = new ReentrantLock();
        addLock = new ReentrantLock();
        getConnectionCondition = leisureIndicesLock.newCondition();
        requestQueue = new ConcurrentLinkedQueue<NicoConnection>();
    }

    public boolean isFull() {
        return poolCursor.get() == maxSize;
    }

    public boolean hasLeisure() {
        return leisureIds.size() > 0;
    }

    public NicoConnection getConnection(long wait) {
        Integer index = leisureIds.get(0);
        if(index != null) {
            NicoConnection nc = pools[index];
            if(nc.isClosed()) {
                nc.setUsed(true);
                leisureIds.remove(nc.getId());
                return nc;
            }
        }
        try {
            long time = getConnectionCondition.awaitNanos(wait);
            if(time > 0) {
                return getConnection(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取连接超时");
    }

    public NicoConnection addUsedConnection(NicoConnection nc) {
        addLock.lock();
        Integer id = poolCursor.incrementAndGet();
        pools[id] = nc;
        nc.setId(id);
        addLock.unlock();
        return nc;
    }

    public NicoConnection addUnUsedConnection(NicoConnection nc) {
        addLock.lock();
        Integer id = poolCursor.incrementAndGet();
        pools[id] = nc;
        nc.setUsed(false);
        nc.setId(id);
        addLock.unlock();
        return nc;
    }

    public Condition getGetConnectionCondition() {
        return getConnectionCondition;
    }

    public void setGetConnectionCondition(Condition getConnectionCondition) {
        this.getConnectionCondition = getConnectionCondition;
    }

    public void addToLeisureIds(NicoConnection nc) {
        leisureIds.add(nc.getId());
        getConnectionCondition.signal();
    }

}
