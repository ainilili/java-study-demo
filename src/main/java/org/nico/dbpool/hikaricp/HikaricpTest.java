package org.nico.dbpool.hikaricp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class HikaricpTest {

    static ThreadPoolExecutor tpe = new ThreadPoolExecutor(1000, 1000, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    
    public static void main(String[] args) throws SQLException, InterruptedException {
        long start = System.currentTimeMillis();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&transformedBitIsBoolean=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMaximumPoolSize(100);
        dataSource.setMinimumIdle(100);
        dataSource.setIdleTimeout(6000000);

        test(dataSource, 10000);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
    
    public static void test(DataSource dataSource, int count) throws SQLException, InterruptedException {
        
        CountDownLatch cdl = new CountDownLatch(count);
        for(int i = 0; i < count; i ++) {
            tpe.execute(() -> {
                try {
                    Connection connection = dataSource.getConnection();
                    Statement s = connection.createStatement();
                    ResultSet rs = s.executeQuery("select * from test limit 0,1");
                    rs.next();
                    connection.close();
                }catch(Exception e) {
                }finally {
                    cdl.countDown();
                }
            });
        }
        cdl.await();
        tpe.shutdown();
    }
    
}
