package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.db.ConnectionPool;
import by.gourianova.apptrainer.db.ProxyConnection;
import by.gourianova.apptrainer.exception.DaoException;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.HttpAddress;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HttpAddressDao extends AbstractDao<HttpAddress> {

    private final static String SQL_CHECK_APPS_ON_HTTPADDRESS = "SELECT COUNT(*) FROM apps WHERE HttpAddresses_Id = ? AND In_Rent = 0;";
    private final static String SQL_FIND_ALL_HTTPADDRESSES = "SELECT * FROM httpaddresses;";
    private final static String SQL_CREATE_HTTPADDRESS = "INSERT INTO httpaddresses (Web_shop, Location) VALUES (?, ?);";
    private final static String SQL_FIND_HTTPADDRESS_BY_ID = "SELECT * FROM httpaddresses WHERE id = ?;";

    @Override
    public ArrayList<HttpAddress> findAll() throws DaoException {
        ArrayList<HttpAddress> httpAddressesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_HTTPADDRESSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HttpAddress httpAddress = new HttpAddress();
                httpAddress.setId(resultSet.getInt(1));
                httpAddress.setWeb_shop(resultSet.getString(2));
                httpAddress.setLocation(resultSet.getString(3));
                httpAddressesList.add(httpAddress);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return httpAddressesList;
    }

    @Override
    public HttpAddress findEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        HttpAddress httpAddress = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_HTTPADDRESS_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                httpAddress = new HttpAddress();
                httpAddress.setId(resultSet.getInt(1));
                httpAddress.setWeb_shop(resultSet.getString(2));
                httpAddress.setLocation(resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return httpAddress;
    }

    @Override
    public boolean createEntity(HttpAddress entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_HTTPADDRESS);
            preparedStatement.setString(1, entity.getWeb_shop());
            preparedStatement.setString(2, entity.getLocation());
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreate;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        return false;
    }

    public Integer checkNumberAppOnHttpAddress(Integer httpAddressId) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        int appsCount = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_APPS_ON_HTTPADDRESS);
            preparedStatement.setInt(1, httpAddressId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                appsCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in checkNumberAppOnHttpAddress method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return appsCount;
    }
}
