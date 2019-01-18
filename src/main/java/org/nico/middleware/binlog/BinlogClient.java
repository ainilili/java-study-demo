package org.nico.middleware.binlog;

import java.io.IOException;
import java.util.List;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer.CompatibilityMode;
import com.github.shyiko.mysql.binlog.network.AuthenticationException;
import com.github.shyiko.mysql.binlog.network.ServerException;

public class BinlogClient extends BinaryLogClient{

    protected EventDeserializer eventDeserializer = new EventDeserializer();

    protected List<CompatibilityMode> compatibilityModes;
    
    protected BinlogListener listener;

    public BinlogClient(String hostname, int port, String schema, String username, String password) {
        super(hostname, port, schema, username, password);
    }

    public BinlogClient(String hostname, int port, String username, String password) {
        super(hostname, port, username, password);
    }

    public BinlogClient(String schema, String username, String password) {
        super(schema, username, password);
    }

    public BinlogClient(String username, String password) {
        super(username, password);
    }

    public BinlogClient addCompatibilityMode(CompatibilityMode first, CompatibilityMode... modes) {
        eventDeserializer.setCompatibilityMode(first, modes);
        this.setEventDeserializer(eventDeserializer);
        return this;
    }
    
    public BinlogClient addListener(BinlogListener listener) {
        this.listener = listener;
        this.registerEventListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                listener.call(event);
            }
        });
        return this;
    }
    
    public void connect(){
        try {
            super.connect();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }catch (ServerException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void connectGuaranteed() {
        try {
            super.connect();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }catch (ServerException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
