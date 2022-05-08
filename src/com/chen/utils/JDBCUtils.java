package com.chen.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取和释放数据库连接
 */

public class  JDBCUtils {
    private static DataSource ds = new ComboPooledDataSource("webDataSource");
    private static Map<Long, Connection> conns = new HashMap<>();

    /**
     * 获取数据库连接
     *
     * @return
     */
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            connection = ds.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
    public static Connection getConnection() {
        long id = Thread.currentThread().getId();
        System.out.println("JDBCUtils中的线程号: " + id);
        //获取当前线程的连接
        Connection connection = conns.get(Thread.currentThread().getId());
        if (connection == null){
            try {
                //当第一次要连接的时候，map中没有，新建一个连接，把他保存到Map中
                connection = ds.getConnection();
//                connection.setAutoCommit(false);
                //把连接保存在Map中
                conns.put(id, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 释放数据库连接
     */
//    public static void releaseConnection(Connection connection) {
//        try {
//            if (connection != null)
//                connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void releaseConnection() {
        //获取当前线程的连接
        Connection connection = getConnection();
        try {
                connection.close();
                conns.remove(Thread.currentThread().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
