package org.nico.middleware.binlog;

import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.RowsQueryEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

public class BinlogDefaultCallBack implements BinlogCallBack{

    @Override
    public void onInsert(WriteRowsEventData event) {
        System.out.println("Data：" + event.getRows());
    }

    @Override
    public void onUpdate(UpdateRowsEventData event) {
        System.out.println("Data：" + event.getRows());
    }

    @Override
    public void onDelete(DeleteRowsEventData event) {
        System.out.println("Data：" + event.getRows());
    }

    @Override
    public void onSql(RowsQueryEventData data) {
        System.out.println("Sql：" + data.getQuery());
    }


}
