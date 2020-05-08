package utils;

import java.sql.*;

/**
 * @author Ji Rui
 * @date 2020/4/27
 */

public class CreateDb {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String NEW_URL = "jdbc:mysql://localhost:3306/web";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String CHECK_DATABASE = "SHOW DATABASES LIKE 'web'";
    private static final String CREATE_DATABASE = "CREATE DATABASE web";

    private static final String CREATE_TABLE_USER_REGISTER = "CREATE TABLE User_Register " +
            "(id VARCHAR(11) NOT NULL PRIMARY KEY," +
            "username VARCHAR(10) NOT NULL," +
            "password VARCHAR(32)NOT NULL," +
            "name VARCHAR(10) NOT NULL," +
            "academy VARCHAR(25) NOT NULL," +
            "grade VARCHAR(5) NOT NULL,"+
            "major VARCHAR(25) NOT NULL," +
            "classname VARCHAR(10) NOT NULL," +
            "phone VARCHAR(15) NOT NULL," +
            "INDEX (id)," +
            "INDEX (name))";

    private static final String CREATE_TABLE_STUDENT_INFO = "CREATE TABLE Student_Info " +
            "(id VARCHAR(11) NOT NULL PRIMARY KEY," +
            "fever TINYINT(1) NOT NULL," +
            "healthy TINYINT(1) NOT NULL," +
            "patients TINYINT(1) NOT NULL," +
            "foreigners TINYINT(1) NOT NULL," +
            "danger TINYINT(1) NOT NULL)";

    private static final String CREATE_TABLE_SIGN_IN = "CREATE TABLE Sign_In "+
            "(id VARCHAR(11) NOT NULL PRIMARY KEY," +
            "name VARCHAR(10) NOT NULL," +
            "date DATE," +
            "signed TINYINT(1) NOT NULL DEFAULT (0)," +
            "CONSTRAINT FK_Name FOREIGN KEY (name) REFERENCES user_register (name) ON DELETE CASCADE ON UPDATE CASCADE," +
            "CONSTRAINT FK_ID FOREIGN KEY (id) REFERENCES user_register (id) ON DELETE CASCADE ON UPDATE CASCADE," +
            "INDEX (id)," +
            "INDEX (name))";

    /**
     * 级联删除
     */
    private static final String ADD_FOREIGN_KEY_DELETE_INFO = "ALTER TABLE Student_Info " +
            "ADD constraint DELETE_User_Info " +
            "FOREIGN KEY(id) REFERENCES User_Register(id) " +
            "ON DELETE CASCADE";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName(DB_DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(CHECK_DATABASE);
        if (rs.next()) {
            statement.close();
            connection.close();
        } else {
            if (1 == statement.executeUpdate(CREATE_DATABASE)) {
                //打开创建的数据库
                connection = DriverManager.getConnection(NEW_URL, USER, PASSWORD);
                statement = connection.createStatement();
                //创建相同结构的表
                statement.execute(CREATE_TABLE_USER_REGISTER);
                statement.execute(CREATE_TABLE_STUDENT_INFO);
                statement.execute(ADD_FOREIGN_KEY_DELETE_INFO);
                statement.execute(CREATE_TABLE_SIGN_IN);
                statement.close();
                connection.close();
            }
        }
    }
}