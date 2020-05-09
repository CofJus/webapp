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
                " (id,user_id,name,date,signed)" +
                "VALUES (?,?,?,?,?)";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(insert);
            preparedStatement.setString(2, sign.getUserId());
            preparedStatement.setString(3, sign.getName());
            preparedStatement.setDate(4, sign.getDate());
            preparedStatement.setInt(5, sign.getSigned());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public Sign queryById(String userId,Date date) {
        Sign sign = new Sign();
        final String queryById = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE user_id=?" +
                "AND date=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryById);
            preparedStatement.setString(1, userId);
            preparedStatement.setDate(2,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sign.setId(resultSet.getInt(1));
                sign.setUserId(resultSet.getString(2));
                sign.setName(resultSet.getString(3));
                sign.setDate(resultSet.getDate(4));
                sign.setSigned(resultSet.getInt(5));
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
    public Sign queryByName(String name,Date date) {
        Sign sign = new Sign();
        final String queryByName = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE name=?" +
                "AND date=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryByName);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sign.setId(resultSet.getInt(1));
                sign.setUserId(resultSet.getString(2));
                sign.setName(resultSet.getString(3));
                sign.setDate(resultSet.getDate(4));
                sign.setSigned(resultSet.getInt(5));
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
    public ArrayList<Sign> queryByStates(Date date,int state){
        ArrayList<Sign> arrayList=new ArrayList<>();
        final String queryBySigned = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE signed=?" +
                "AND date=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryBySigned);
            preparedStatement.setInt(1, state);
            preparedStatement.setDate(2,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sign sign = new Sign();
                sign.setId(resultSet.getInt(1));
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
