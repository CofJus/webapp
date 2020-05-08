package dao.impl;

import dao.SignDao;
import utils.DbConnect;
import vo.Sign;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Ji Rui
 */
public class SignDaoImpl implements SignDao {

    private final String TABLE_NAME = "Sign_In";

    @Override
    public void insert(Sign sign) {
        final String insert = "INSERT INTO " +
                TABLE_NAME +
                " (id,name,date,signed)" +
                "VALUES (?,?,?,?)";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, sign.getId());
            preparedStatement.setString(2, sign.getName());
            preparedStatement.setDate(3, sign.getDate());
            preparedStatement.setInt(4, sign.getSigned());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public void updateById(String id) {

        Date currentDate=new Date(System.currentTimeMillis());
        DbConnect dbConnect = new DbConnect();
        final String update = "UPDATE " +
                TABLE_NAME +
                " SET signed = ?," +
                "date = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(update);
            preparedStatement.setInt(1, 1);
            preparedStatement.setDate(2,currentDate);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public Sign queryById(String id) {
        Sign sign = new Sign();
        final String queryById = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE id=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryById);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sign.setId(resultSet.getString(1));
                sign.setName(resultSet.getString(2));
                sign.setDate(resultSet.getDate(3));
                sign.setSigned(resultSet.getInt(4));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
        return sign;
    }

    @Override
    public Sign queryByName(String name) {
        Sign sign = new Sign();
        final String queryByName = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE name=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryByName);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sign.setId(resultSet.getString(1));
                sign.setName(resultSet.getString(2));
                sign.setDate(resultSet.getDate(3));
                sign.setSigned(resultSet.getInt(4));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
        return sign;
    }

    @Override
    public ArrayList<Sign> queryBySigned() {
        ArrayList<Sign> arrayList=new ArrayList<>();
        final String queryBySigned = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE signed=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryBySigned);
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sign sign = new Sign();
                sign.setId(resultSet.getString(1));
                sign.setName(resultSet.getString(2));
                sign.setDate(resultSet.getDate(3));
                sign.setSigned(resultSet.getInt(4));
                arrayList.add(sign);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
        return arrayList;
    }
}
