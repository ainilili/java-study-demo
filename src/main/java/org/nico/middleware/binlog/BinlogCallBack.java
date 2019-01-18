package org.nico.middleware.binlog;

import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.RowsQueryEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

public interface BinlogCallBack {

    public void onInsert(WriteRowsEventData data);
    
    public void onUpdate(UpdateRowsEventData data);
    
    public void onDelete(DeleteRowsEventData data);
    
    public void onSql(RowsQueryEventData data);
}
