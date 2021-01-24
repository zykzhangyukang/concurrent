package com.coderman.concurrent.threadlocal;

import com.coderman.concurrent.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author zhangyukang
 * @Date 2021/1/24 11:25
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ThreadLocalExample2 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String username="root";
        String password="zhangyukang";
        String jdbcURL="jdbc:mysql://127.0.0.1:3307/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
        connection.setAutoCommit(false);
        new Thread(()->{
            try {
                ConnectionHolder.setCurrentConnection(connection);
                sql1(connection);
                int a=2/0;
                ConnectionHolder.getCurrentConnection().commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                try {
                    ConnectionHolder.getCurrentConnection().rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }finally {
                ConnectionHolder.remove();
            }
        }).start();

        new Thread(()->{
            try {
                ConnectionHolder.setCurrentConnection(connection);
                sql2(connection);
                ConnectionHolder.getCurrentConnection().commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                try {
                    ConnectionHolder.getCurrentConnection().rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }finally {
                ConnectionHolder.remove();
            }
        }).start();
    }

    private static void sql1(Connection connection) throws SQLException {
        String sql="delete from user where id=2";
        Statement statement = connection.createStatement();
        boolean execute = statement.execute(sql);
    }

    private static void sql2(Connection connection) throws SQLException {
        String sql="delete from user where id=3";
        Statement statement = connection.createStatement();
        boolean execute = statement.execute(sql);
    }
}

class ConnectionHolder{
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL=new ThreadLocal<>();

    public static void setCurrentConnection(Connection currentConnection){
        CONNECTION_THREAD_LOCAL.set(currentConnection);
    }

    public static Connection getCurrentConnection(){
        return CONNECTION_THREAD_LOCAL.get();
    }

    public static void remove(){
        CONNECTION_THREAD_LOCAL.remove();
    }
}

