package org.nico.middleware.test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.junit.Test;
import org.nico.middleware.binlog.BinlogClient;
import org.nico.middleware.binlog.BinlogDefaultCallBack;
import org.nico.middleware.binlog.BinlogDefaultListener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.GtidEventData;
import com.github.shyiko.mysql.binlog.event.QueryEventData;
import com.github.shyiko.mysql.binlog.event.RowsQueryEventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.github.shyiko.mysql.binlog.event.XidEventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

public class BinlogTest {

    @Test
    public void testReader() throws IOException {

        File binlogFile = new File("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Data\\XIPDZ9YMV9RR8OI-bin.000002");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
                );
        BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer);
        try {
            for (Event event; (event = reader.readEvent()) != null; ) {
                System.out.println(event);
            }
        } finally {
            reader.close();
        }
    }

    @Test
    public void testListener() throws IOException {
        BinaryLogClient client = new BinaryLogClient("localhost", 3306, "root", "root");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
                );
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new EventListener() {

            @Override
            public void onEvent(Event event) {
                switch (event.getHeader().getEventType()) {
                case ANONYMOUS_GTID:
                    System.out.println(((GtidEventData) event.getData()));
                    break;
                case QUERY:
                    System.out.println(((QueryEventData) event.getData()));
                    break;
                case ROWS_QUERY:
                    System.out.println(((RowsQueryEventData) event.getData()));
                    break;
                case TABLE_MAP:
                    System.out.println(((TableMapEventData) event.getData()));
                    break;
                case EXT_WRITE_ROWS:
                    WriteRowsEventData data = (WriteRowsEventData) event.getData();
                    for(Serializable s: data.getRows().get(0)) {
                        if(s instanceof byte[]) {
                            System.out.println(new String((byte[])s));
                        }else {
                            System.out.println(s);
                        }
                    }
                    break;
                case EXT_UPDATE_ROWS:
                    UpdateRowsEventData data1 = (UpdateRowsEventData) event.getData();
                    for(Map.Entry<Serializable[], Serializable[]> e: data1.getRows()) {
                        System.out.println(e.getKey()[1] + "-" + e.getValue()[1]);
                    }
                    break;
                case XID:
                    System.out.println(((XidEventData) event.getData()));
                    break;
                default:
                    break;
                }
//                System.out.println(event.getHeader().getEventType());
            }
        });
        client.connect();
    }
    
    @Test
    public void test3() {
        new BinlogClient("localhost", 3306, "root", "root")
        .addCompatibilityMode(EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY)
        .addListener(new BinlogDefaultListener(new BinlogDefaultCallBack()))
        .keepAliveInterval(100)
        .start();
    }
}
