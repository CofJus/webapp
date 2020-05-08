package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ji Rui
 */

public class DbConnect {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/web";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private Connection connection = null;

    public DbConnect() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("加载驱动失败");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeDb() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}