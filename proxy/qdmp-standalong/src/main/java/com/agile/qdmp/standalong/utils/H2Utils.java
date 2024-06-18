package com.agile.qdmp.standalong.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: wenbinglei
 * @Date: 2022/11/8 19:04
 * @Description: https://www.cnblogs.com/xdp-gacl/p/4171024.html
 */
public class H2Utils {
    // 定义连接常量
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:D:/mysoft/h2-2022-06-13/h2/my_data/java_test;MODE=MySQL";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection conn = getConnection();

    // 测试
    public static void main(String[] args) throws SQLException {
        System.out.println(conn);
    }

    // jdbc连接H2数据库
    private static Connection getConnection() {
        if(null != conn) {
            return conn;
        }
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (null != conn) {System.out.println("H2数据库连接成功！");}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;

    }
}
