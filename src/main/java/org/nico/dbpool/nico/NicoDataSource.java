package org.nico.dbpool.nico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class NicoDataSource extends NicoDataSourceWrapper implements DataSource{

    private String url;
    
    private String username;
    
    private String password;
    
    private String driver;
    
    private int init;
    
    private int max;
    
    private int min;
    
    private long waitTime;
    
    private NicoConnectionPool pool;
    
    private volatile boolean isInit;
    
    @Override
    public Connection getConnection() throws SQLException {
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        if(pool.hasLeisure()) {
            return pool.getConnection(waitTime);
        }
        
        if(! pool.isFull()) {
            return pool.addUsedConnection(createNativeConnection(pool));
        }
        
        return pool.getConnection(waitTime);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }
    
    private void init() throws ClassNotFoundException, SQLException {
        if(isInit) {
            return;
        }
        isInit = true;
        
        Class.forName(driver);
        
        pool = new NicoConnectionPool(max);
        
        for(int i = 0; i < init; i ++) {
            pool.addUnUsedConnection(createNativeConnection(pool));
        }
    }
    
    public NicoConnection createNativeConnection(NicoConnectionPool pool) throws SQLException {
        return new NicoConnection(DriverManager.getConnection(url, username, password), pool);
    }

}
