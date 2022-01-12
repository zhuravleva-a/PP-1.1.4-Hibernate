package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/users?useSSL=false";
    //private static final String URL = "jdbc:mysql://localhost:3306/users?useSSL=true&enabledTLSProtocols=TLSv1.2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Radiocof1";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
