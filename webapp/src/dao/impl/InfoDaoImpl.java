package dao.impl;

import dao.InfoDao;
import utils.DbConnect;
import vo.Info;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Ji Rui
 */
public class InfoDaoImpl implements InfoDao {

    private final String TABLE_NAME = "Student_Info";

    @Override
    public void insert(Info info) {
        DbConnect dbConnect = new DbConnect();
        final String insert = "INSERT INTO " +
                TABLE_NAME +
                "(id,fever,healthy,foreigners,danger,date)" +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, info.getId());
            preparedStatement.setInt(2, info.getHasFever());
            preparedStatement.setInt(3, info.getIsHealthy());
            preparedStatement.setInt(4, info.getHasContactWithForeigners());
            preparedStatement.setInt(5, info.getIsDanger());
            preparedStatement.setDate(6,info.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public void updateById(Info info) {
        DbConnect dbConnect = new DbConnect();
        final String update = "UPDATE " +
                TABLE_NAME +
                " SET id = ?," +
                "fever = ?," +
                "healthy = ?," +
                "foreigners=?," +
                "danger=?," +
                "date=?" +
                "WHERE id=?";
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(update);
            preparedStatement.setString(1, info.getId());
            preparedStatement.setInt(2, info.getHasFever());
            preparedStatement.setInt(3, info.getIsHealthy());
            preparedStatement.setInt(4, info.getHasContactWithForeigners());
            preparedStatement.setInt(5, info.getIsDanger());
            preparedStatement.setDate(5,info.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
    }

    @Override
    public Info queryById(String id) {
        Info info = new Info();
        final String queryById = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE id=?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryById);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                info.setId(resultSet.getString(1));
                info.setHasFever(resultSet.getInt(2));
                info.setIsHealthy(resultSet.getInt(3));
                info.setHasContactWithForeigners(resultSet.getInt(4));
                info.setIsDanger(resultSet.getInt(5));
                info.setDate(resultSet.getDate(6));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            dbConnect.closeDb();
        }
        return info;
    }

    @Override
    public ArrayList<Info> queryAll() {
        ArrayList<Info> arrayList = new ArrayList<>();
        final String queryAll = "SELECT * FROM " +
                TABLE_NAME;
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Info info = new Info();
                info.setId(resultSet.getString(1));
                info.setHasFever(resultSet.getInt(2));
                info.setIsHealthy(resultSet.getInt(3));
                info.setHasContactWithForeigners(resultSet.getInt(4));
                info.setIsDanger(resultSet.getInt(5));
                info.setDate(resultSet.getDate(6));
                arrayList.add(info);
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

    @Override
    public ArrayList<Info> queryByDanger() {
        ArrayList<Info> arrayList = new ArrayList<>();
        final String queryAll = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE danger = ?";
        DbConnect dbConnect = new DbConnect();
        try {
            PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(queryAll);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Info info = new Info();
                info.setId(resultSet.getString(1));
                info.setHasFever(resultSet.getInt(2));
                info.setIsHealthy(resultSet.getInt(3));
                info.setHasContactWithForeigners(resultSet.getInt(4));
                info.setIsDanger(resultSet.getInt(5));
                info.setDate(resultSet.getDate(6));
                arrayList.add(info);
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
