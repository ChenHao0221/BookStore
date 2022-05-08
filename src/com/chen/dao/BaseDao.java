package com.chen.dao;

import com.chen.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {

    private QueryRunner runner = new QueryRunner();
    private  Class<T> type; //泛型接收器

    public BaseDao() {
        //获取父类的类型，父类是带参数的
        //子类在继承的时候会调用（super）父类（也就是BaseDao）的构造器
        ParameterizedType superclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        //System.out.println(superclass.getClass());
        //获取参数的类型
        type = (Class<T>) superclass.getActualTypeArguments()[0];
    }

    /**
     * 获取一个对象
     * @return
     */
    public T getBean(String sql, Object ...params){
        Connection connection = JDBCUtils.getConnection();
        T query = null;
        try {
            query = runner.query(connection, sql, new BeanHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection();
        }
        return query;
    }

    /**
     *获取对象的集合
     * @return
     */
    public List<T> getBeanList(String sql, Object ...params){
        //获取数据库链接
        Connection connection = JDBCUtils.getConnection();
        List<T> query = null;
        try {
            //查询一组数据
            query = runner.query(connection, sql, new BeanListHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection();
        }
        return query;
    }

    /**
     * 执行增删改
     * @return
     */
    public int update(String sql, Object ...params){
        int count = 0;
        Connection connection = JDBCUtils.getConnection();
        try {
            count = runner.update(connection, sql, params); //sql表示sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection();
        }
        return count;
    }

    /**
     * 查询单个值
     * @param sql
     * @param params
     * @return
     */
    public Object getSingleValue(String sql, Object ...params){
        Object query = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            query = runner.query(connection, sql, new ScalarHandler(), params);//sql表示sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection();
        }
        return query;
    }

    /**
     * 批处理sql语句
     * @param sql
     * @param params
     * @return
     */
    public int batch(String sql, Object[][] params){
        Connection connection = JDBCUtils.getConnection();
        try {
            //Object[][] params
            //第一维表示sql执行的次数
            //第二维专门用来存放当前sql执行要用的可变参数
            runner.batch(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection();
        }
        return 0;
    }
}
