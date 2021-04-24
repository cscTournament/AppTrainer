package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.db.ConnectionPool;
import by.gourianova.apptrainer.db.ProxyConnection;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.exception.DaoException;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
public class AppDao extends AbstractDao<App> {


    private final static String SQL_FIND_ALL_APPS = "SELECT apps.Id ,  apps.Title, types.Price_Per_Hour,   httpaddresses.Web_shop, httpaddresses.Location, types.Image  FROM apps, types, httpaddresses WHERE apps.HttpAddresses_Id = httpaddresses.Id AND apps.Types_Id = types.Id ;";

    private final static String SQL_FIND_BY_PAGE = "SELECT apps.Id ,  apps.Title, types.Price_Per_Hour,  types.Image, apps.HttpAddresses_Id FROM apps, types, httpaddresses WHERE apps.HttpAddresses_Id = httpaddresses.Id AND apps.Types_Id = types.Id AND apps.In_Rent = '0' ORDER BY apps.Id LIMIT ? OFFSET ?;";
    private final static String SQL_FIND_BY_ID = "SELECT apps.Id ,  apps.Title, types.Price_Per_Hour,  types.Image, apps.HttpAddresses_Id FROM apps, types, httpaddresses WHERE apps.Id = ? AND  apps.HttpAddresses_Id = httpaddresses.Id AND apps.Types_Id = types.Id ORDER BY apps.Id;";
    private final static String SQL_FIND_APP = "SELECT apps.Id ,  apps.Title, types.Price_Per_Hour,  types.Image, apps.HttpAddresses_Id  FROM apps, types, httpaddresses WHERE apps.Id = ? AND apps.HttpAddresses_Id = httpaddresses.Id AND apps.Types_Id = types.Id;";
    private final static String SQL_RENT_TRUE = "UPDATE apps SET In_Rent = '1' WHERE id = ?;";
    private final static String SQL_ADD_ORDER = "INSERT INTO orders (Start_Date, Users_Id, Apps_Id) VALUES (now(), ?, ?);";
    private final static String SQL_SET_USER_ROLE_HAS_ORDER = "UPDATE users SET Roles_Id=17 WHERE Id=?;";
    private final static String SQL_CREATE_APP = "INSERT INTO apps (Title, Types_Id, HttpAddresses_Id) VALUES (?, ?, ?);";
    /*SELECT apps.Id, Type, Price_Per_Hour, Web_shop, Location, Image FROM apps JOIN types ON apps.Types_Id = types.Id JOIN httpaddresses ON apps.HttpAddresses_Id = stations.Id WHERE apps.In_Rent=0 ORDER BY apps.Id;*/
    public final static String GOOGLE_PLAY_RELEASE = "/store/apps/details?id=";
    public final static String GOOGLE_CHROM_RELEASE = "/webstore/detail/binocularvision";

    @Override
    public ArrayList<App> findAll() throws DaoException {
        ArrayList<App> appsList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_APPS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                App app = buildApp(resultSet);
                appsList.add(app);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return appsList;
    }

    @Override
    public App findEntityById(Integer id) throws DaoException {
        App app = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_APP);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                app = buildApp(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByID method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return app;
    }

    @Override
    public boolean createEntity(App entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate = false;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_APP);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getTypeId());
            preparedStatement.setInt(3, entity.getHttpAddressesId());
            preparedStatement.executeUpdate();
            System.out.println("DAOApp.createEntity OK ");
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

    public ArrayList<App> findAllByPage(int pageCapacity, int pageNumber) throws DaoException {
        ArrayList<App> appsList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_PAGE);
            preparedStatement.setInt(1, pageCapacity);
            preparedStatement.setInt(2, (pageNumber * pageCapacity - pageCapacity));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                App app = buildApp(resultSet);
                appsList.add(app);

            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllByPage method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return appsList;
    }

    public App rentApp(Integer appId, Integer userId) throws DaoException {
        App app = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
       
        try {
            connection = ConnectionPool.getInstance().getConnection();
            System.out.println("con +");
            preparedStatement = connection.prepareStatement(SQL_ADD_ORDER);
        /*    preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, appId);
            ResultSet resultSet = preparedStatement.executeQuery();
           // connection.setAutoCommit(false);
            if (resultSet.next()) {
                app = buildApp(resultSet);
                */
            //app.setInRent(true);
            //preparedStatement = connection.prepareStatement(SQL_RENT_TRUE);
            //preparedStatement.setInt(1, app.getId());
            //preparedStatement.executeUpdate();
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, appId);
            preparedStatement.executeUpdate();
            System.out.println("order added +");
            preparedStatement = connection.prepareStatement(SQL_SET_USER_ROLE_HAS_ORDER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("role changed to 17 +");
            //   connection.commit();
            //  connection.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Error in rentApp method", e);
            }
            throw new DaoException("Error in rentApp method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return app;
    }


    private App buildApp(ResultSet resultSet) throws SQLException {
        App app = new App();
        app.setId(resultSet.getInt(1));
        app.setTitle(resultSet.getString(2));
        app.setPricePerHour(resultSet.getBigDecimal(3));
        app.setWeb_shop(resultSet.getString(4));
        app.setLocation(resultSet.getString(5));
        app.setUrl(createUrL(resultSet.getString(4), resultSet.getString(5)));
        Blob photo = resultSet.getBlob(6);
        if (photo != null) {
            byte[] picture = photo.getBytes(1, (int) photo.length());
            String pic = Base64.getEncoder().encodeToString(picture);
            app.setPicture(pic);
        }
        return app;
    }

    private String createUrL(String web_shop, String location) {
        String url = "";
        if (web_shop != null) {
            if (web_shop.contains("play.google.com")) {
                url = "http://" + "play.google.com" + GOOGLE_PLAY_RELEASE + location;

            } else if (web_shop.contains("chrome.google.com")) {
                url = "http://" + "chrome.google.com" + GOOGLE_CHROM_RELEASE + location;

            } else if (web_shop.contains("csc")) {
                url = "http://" + "csc." + location;

            } else {
                url = "http://csc.buxar-host.ru/csc_tournament_2020/";
            }
        }

        return url;
    }

}
