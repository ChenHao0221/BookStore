package com.chen.test;

import com.chen.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class JDBCUtilsTest {

    //加了@Test之后才能测试这个方法，不然没办法运行测试
    @Test
    public void getConnection(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        JDBCUtils.releaseConnection();
    }
}
