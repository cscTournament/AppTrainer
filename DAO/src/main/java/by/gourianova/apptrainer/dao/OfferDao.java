package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.db.ConnectionPool;
import by.gourianova.apptrainer.db.ProxyConnection;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.Offer;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.DaoException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OfferDao extends AbstractDao<Offer> {

    private final static String SQL_FIND_OFFER = "SELECT * FROM offers WHERE Users_Id=? ORDER BY id DESC LIMIT 1;";
    private final static String SQL_FIND_ALL_OFFERS = "SELECT * FROM offers;";
    private final static String SQL_FIND_UNCLOSED_OFFERS = "SELECT * FROM offers WHERE End_Date IS NULL;";
    private final static String SQL_FIND_UNCLOSED_USER_OFFER = "SELECT * FROM offers WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_FIND_ALL_USER_OFFERS = "SELECT * FROM offers WHERE Users_Id=?;";
    private final static String SQL_CLOSE_OFFER = "UPDATE offers SET End_Date=now(), Value=? WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_UPDATE_USER = "UPDATE users SET Balance=?, Roles_Id=2 WHERE Id=?;";
    private final static String SQL_UPDATE_APP = "UPDATE apps SET In_Rent=0 WHERE Id=?;";

    @Override
    public ArrayList<Offer> findAll() throws DaoException {
        ArrayList<Offer> offersList = new ArrayList<>();
        Offer offer;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_OFFERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offer = buildOffer(resultSet);
                offersList.add(offer);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return offersList;
    }

    @Override
    public Offer findEntityById(Integer userId) throws DaoException {
        Offer offer = new Offer();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_OFFER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offer = buildOffer(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return offer;
    }

    @Override
    public boolean createEntity(Offer entity) throws DaoException {

        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        return false;
    }


    public Offer findUnclosedOffer(Integer userId) throws DaoException {
        Offer offer = new Offer();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_USER_OFFER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offer.setId(resultSet.getInt(1));
                offer.setDescription(resultSet.getObject(2).toString());
                offer.setAppId(resultSet.getInt(3));
                            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosedOffer method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return offer;
    }

    public ArrayList<Offer> findAllUserOffers(Integer userId) throws DaoException {
        ArrayList<Offer> offersList = new ArrayList<>();
        Offer offer;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER_OFFERS);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offer = buildOffer(resultSet);
                offersList.add(offer);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllUserOffers method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return offersList;
    }

    public ArrayList<Offer> findUnclosed() throws DaoException {
        ArrayList<Offer> offersList = new ArrayList<>();
        Offer offer;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_OFFERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offer = buildOffer(resultSet);
                offersList.add(offer);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosed method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return offersList;
    }

    public void closeOffer(User user, BigDecimal value, App app) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_CLOSE_OFFER);
            preparedStatement.setBigDecimal(1, value);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setBigDecimal(1, user.getBalance().subtract(value));
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APP);
            preparedStatement.setInt(1, app.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Error in closeOffer method", e);
            }
            throw new DaoException("Error in closeOffer method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private Offer buildOffer(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setId(resultSet.getInt(1));
        offer.setDescription(resultSet.getObject(2).toString());
        offer.setAppId(resultSet.getInt(3));;
        return offer;
    }
}
