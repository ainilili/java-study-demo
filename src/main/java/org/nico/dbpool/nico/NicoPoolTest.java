package org.nico.dbpool.nico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NicoPoolTest {

    public static void main(String[] args) throws SQLException {
        NicoDataSource dataSource = new NicoDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&transformedBitIsBoolean=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setMax(2);
        dataSource.setInit(1);
        dataSource.setWaitTime(Long.MAX_VALUE);
        dataSource.setMin(2);
        
        NicoConnection connection = (NicoConnection) dataSource.getConnection();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("select * from test limit 0,1");
        rs.next();
        System.out.println(rs.getInt(1));
        
        
        final NicoConnection connection1 = (NicoConnection) dataSource.getConnection();
        s = connection1.createStatement();
        rs = s.executeQuery("select * from test limit 0,1");
        rs.next();
        System.out.println(rs.getInt(1));
        
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connection1.close();
            }
        }.start();
        
        
        connection = (NicoConnection) dataSource.getConnection();
        s = connection.createStatement();
        rs = s.executeQuery("select * from test limit 0,1");
        rs.next();
        System.out.println(rs.getInt(1));
        
    }
}
