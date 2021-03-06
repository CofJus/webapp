package dao.impl;

import dao.UserDao;
import utils.DbConnect;
import vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.Md5Util.doubleSalt;

/**
 * @author Ji Rui
 */

public class UserDaoImpl implements UserDao {
    private final String TABLE_NAME = "User_Register";

    @Override
    public void insert(User user) {

        final String insert = "INSERT INTO " +
                TABLE_NAME +
                " (id,username,password,name,academy,grade,major,classname,phone,country,province,city)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, doubleSalt(user.getPassword()));
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getAcademy());
            preparedStatement.setString(6, user.getGrade());
            preparedStatement.setString(7, user.getMajor());
            preparedStatement.setString(8, user.getClassName());
            preparedStatement.setString(9, user.getPhoneNumber());
            preparedStatement.setString(10,user.getCountry());
            preparedStatement.setString(11,user.getProvince());
            preparedStatement.setString(12,user.getCity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public void updatePassword(String id, String password) {
        DbConnect dbConnect = new DbConnect();
        final String update = "UPDATE " +
                TABLE_NAME +
                " SET password = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(update);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public User queryById(String id) {
        User user = new User();
        final String queryById = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE id=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryById);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getString(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(null);
                user.setName(resultSet.getString(4));
                user.setAcademy(resultSet.getString(5));
                user.setGrade(resultSet.getString(6));
                user.setMajor(resultSet.getString(7));
                user.setClassName(resultSet.getString(8));
                user.setPhoneNumber(resultSet.getString(9));
                user.setCountry(resultSet.getString(10));
                user.setProvince(resultSet.getString(11));
                user.setCity(resultSet.getString(12));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
        return user;
    }
}
