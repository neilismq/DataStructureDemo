package com.bj.zzq.sort;

import java.sql.*;
import java.util.Date;

public class SortTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "admin";
        Connection conn = DriverManager.getConnection(url, username, password);
        String sql = "insert into sort_example(data_count,algorithm_id,cost,create_time) values(?,?,?,?)";
        PreparedStatement prepareStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prepareStatement.setInt(1, 100);
        prepareStatement.setInt(2, 1);
        prepareStatement.setString(3, "123456");
        prepareStatement.setTimestamp(4, new Timestamp(new Date().getTime()));

        prepareStatement.executeUpdate();
        ResultSet rs = prepareStatement.getGeneratedKeys();
        if (rs.next()) {
            int anInt = rs.getInt(1);
            System.out.println(anInt);
        }


    }
}
