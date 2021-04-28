package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.db.ConnectionPool;
import by.gourianova.apptrainer.db.ProxyConnection;
import by.gourianova.apptrainer.entity.AppType;
import by.gourianova.apptrainer.exception.DaoException;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;


public class AppTypeDao extends AbstractDao<AppType> {
    private final static String SQL_CREATE_TABLE_TYPES = "create table if not exists types(id INT(11) NOT NULL auto_increment," +
            "Type varchar(20), Price_Per_Hour DECIMAL (4,2), Image BLOB,  primary key (id) )  DEFAULT CHARSET=utf8;";
    private final static String SQL_CREATE_TYPE = "INSERT INTO types (Type,Price_Per_Hour,Image) VALUES (?,?,?);";
    private final static String SQL_FIND_ALL_TYPE = "SELECT * FROM types;";
    private final static String SQL_EDIT_PRICE = "UPDATE types SET Price_Per_Hour=? WHERE Id=?;";
    private final static String SQL_CREATE = "INSERT INTO types (Type, Price_Per_Hour, Image) VALUES(?,?,?);";
    private final static String SQL_FIND_TYPE_BY_ID = "SELECT * FROM types WHERE id = ?;";
    private final static String SQL_DELETE_TYPE_BY_ID = "DELETE FROM types WHERE id=?;";

    @Override
    public ArrayList<AppType> findAll() throws DaoException {
        ArrayList<AppType> typesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AppType appType = new AppType();
                appType.setId(resultSet.getInt(1));
                appType.setType(resultSet.getString(2));
                appType.setPrice(resultSet.getBigDecimal(3));
                Blob photo = resultSet.getBlob(4);
                if (photo != null) {
                    byte[] image = photo.getBytes(1, (int) photo.length());
                    String pic = Base64.getEncoder().encodeToString(image);
                    appType.setImage(pic);
                }
                typesList.add(appType);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return typesList;
    }

    @Override
    public AppType findEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        AppType appType = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_TYPE_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                appType = new AppType();
                appType.setId(resultSet.getInt(1));
                appType.setType(resultSet.getString(2));
                appType.setPrice(resultSet.getBigDecimal(3));
                Blob photo = resultSet.getBlob(4);
                byte[] image = photo.getBytes(1, (int) photo.length());
                String pic = Base64.getEncoder().encodeToString(image);
                appType.setImage(pic);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return appType;
    }

    public void create(AppType entity, InputStream image) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE);
            preparedStatement.setString(1, entity.getType());
            preparedStatement.setBigDecimal(2, entity.getPrice());
            preparedStatement.setBlob(3, image);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in create method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public void updateEntity(AppType entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_EDIT_PRICE);
            preparedStatement.setBigDecimal(1, entity.getPrice());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean createEntity(AppType entity) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        boolean isDeleted = false;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_TYPE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            isDeleted = true;
        } catch (SQLException e) {
            throw new DaoException("Error in deleteEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isDeleted;
    }
}
