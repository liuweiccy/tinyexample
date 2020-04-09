package com.digisky.liuwei2.tinyexample.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author liuwei2
 * 2020/4/8 10:42
 */
public class TestSqlite {
    private static Connection CONN = null;

    public static Connection connect() {
        try {
            CONN = DriverManager.getConnection("jdbc:sqlite:data/java_sqlite.db");
            System.out.println("已经连接到SQLite");
            return CONN;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void insert(String name, int age) {
        try {
            String sql = "insert into Student(name, age) values (?,?)";
            PreparedStatement preparedStatement = CONN.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        CONN = connect();
        insert("vv", 30);
        insert("Xin", 3);
        CONN.close();
    }
}
