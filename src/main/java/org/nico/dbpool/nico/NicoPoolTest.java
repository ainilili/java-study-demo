package org.nico.dbpool.nico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

public class NicoPoolTest {

    static ThreadPoolExecutor tpe = new ThreadPoolExecutor(1000, 1000, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    
    public static void main(String[] args) throws SQLException, InterruptedException {
        NicoDataSource dataSource = new NicoDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&transformedBitIsBoolean=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setMax(100);
        dataSource.setInit(100);
        dataSource.setWaitTime(Long.MAX_VALUE);
        dataSource.setMin(100);
        
        test(dataSource, 10000);
    }
    
    public static void test(DataSource dataSource, int count) throws SQLException, InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch cdl = new CountDownLatch(count);
        for(int i = 0; i < count; i ++) {
            tpe.execute(() -> {
                try {
                    NicoConnection connection = (NicoConnection) dataSource.getConnection();
                    Statement s = connection.createStatement();
                    ResultSet rs = s.executeQuery("select * from test limit 0,1");
                    rs.next();
//                    System.out.println("连接ID " + connection.getId());
                    connection.close();
                }catch(Exception e) {
                }finally {
                    cdl.countDown();
                }
            });
        }
        cdl.await();
        System.out.println(System.currentTimeMillis() - start + " ms");
        tpe.shutdown();
    }
}
