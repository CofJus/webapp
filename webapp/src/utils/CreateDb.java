package utils;

import dao.UserDao;
import vo.User;

import java.sql.*;

import static factory.DaoFactory.getUserDaoInstance;
import static utils.Md5Util.doubleSalt;

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
            "grade VARCHAR(5) NOT NULL," +
            "major VARCHAR(25) NOT NULL," +
            "classname VARCHAR(10) NOT NULL," +
            "phone VARCHAR(15) NOT NULL," +
            "country VARCHAR(10) NOT NULL," +
            "province VARCHAR(10) NOT NULL," +
            "city VARCHAR(10) NOT NULL)";

    private static final String CREATE_TABLE_STUDENT_INFO = "CREATE TABLE Student_Info " +
            "(id VARCHAR(11) NOT NULL PRIMARY KEY," +
            "fever TINYINT(1) NOT NULL," +
            "healthy TINYINT(1) NOT NULL," +
            "foreigners TINYINT(1) NOT NULL," +
            "danger TINYINT(1) NOT NULL," +
            "date DATE NOT NULL)";

    private static final String CREATE_TABLE_SIGN_IN = "CREATE TABLE Sign_In " +
            "(id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "user_id VARCHAR(11) NOT NULL," +
            "name VARCHAR(10) NOT NULL," +
            "date DATE," +
            "signed TINYINT(2) NOT NULL DEFAULT (0)," +
            "CONSTRAINT FK_ID FOREIGN KEY (user_id) REFERENCES user_register (id) ON DELETE CASCADE ON UPDATE CASCADE)";

    /**
     * 级联删除
     */
    private static final String ADD_FOREIGN_KEY_DELETE_INFO = "ALTER TABLE Student_Info " +
            "ADD constraint DELETE_User_Info " +
            "FOREIGN KEY(id) REFERENCES User_Register(id) " +
            "ON DELETE CASCADE";

    private static final String PSW = doubleSalt("hhu123456");

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        User user = new User();
        user.setUsername("admin");
        user.setPassword(doubleSalt("hhu123456"));
        user.setName("admin");
        user.setId("admin");
        user.setAcademy("");
        user.setGrade("");
        user.setMajor("");
        user.setClassName("");
        user.setPhoneNumber("");
        user.setCountry("");
        user.setProvince("");
        user.setCity("");

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
        UserDao userDao = getUserDaoInstance();
        userDao.insert(user);
    }
}