package org.nico.middleware.binlog;

import com.github.shyiko.mysql.binlog.event.Event;

public abstract class BinlogListener {
    
    protected BinlogCallBack callback;

    public BinlogListener(BinlogCallBack callback) {
        this.callback = callback;
    }
    
    public abstract void call(Event event);
}
