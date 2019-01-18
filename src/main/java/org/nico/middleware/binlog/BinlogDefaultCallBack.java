package org.nico.middleware.binlog;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.RowsQueryEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

public class BinlogDefaultCallBack implements BinlogCallBack{

    @Override
    public void onInsert(WriteRowsEventData event) {
        System.out.println("Insert Data:" + event.getRows().stream().map(a -> { 
            return Arrays.asList(a).stream().map(s -> {return parseValue(s);}).collect(Collectors.toList());
        }).collect(Collectors.toList()));
    }

    @Override
    public void onUpdate(UpdateRowsEventData event) {
        System.out.println("Update Data：" + event.getRows().stream().map(a -> { 
           Map<Object, Object> map = new HashMap<>();
           Serializable[] keys = a.getKey();
           Serializable[] values = a.getValue();
           for(int index = 0; index < keys.length; index ++) {
               map.put(parseValue(keys[index]), parseValue(values[index]));
           }
           return map;
        }).collect(Collectors.toList()));
    }

    @Override
    public void onDelete(DeleteRowsEventData event) {
        System.out.println("Delete Data:" + event.getRows().stream().map(a -> { 
            return Arrays.asList(a).stream().map(s -> {return parseValue(s);}).collect(Collectors.toList());
        }).collect(Collectors.toList()));
    }

    @Override
    public void onSql(RowsQueryEventData data) {
        System.out.println("Sql：" + data.getQuery());
    }

    public Object parseValue(Serializable o) {
        if(o instanceof byte[]) {
            return new String((byte[]) o);
        }else {
            return o;
        }
    }
}
