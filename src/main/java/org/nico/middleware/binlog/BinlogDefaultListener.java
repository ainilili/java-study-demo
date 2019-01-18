package org.nico.middleware.binlog;

import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventType;

public class BinlogDefaultListener extends BinlogListener{

    public BinlogDefaultListener(BinlogCallBack callback) {
        super(callback);
    }

    @Override
    public void call(Event event) {
        EventType et = event.getHeader().getEventType();
        if(et == EventType.ROWS_QUERY) {
            this.callback.onSql(event.getData());
        }else if(et == EventType.EXT_WRITE_ROWS) {
            this.callback.onInsert(event.getData());
        }else if(et == EventType.EXT_UPDATE_ROWS) {
            this.callback.onUpdate(event.getData());
        }else if(et == EventType.EXT_DELETE_ROWS) {
            this.callback.onDelete(event.getData());
        }
        
    }

}
