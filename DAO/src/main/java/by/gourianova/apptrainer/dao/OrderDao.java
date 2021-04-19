package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.db.ConnectionPool;
import by.gourianova.apptrainer.db.ProxyConnection;
import by.gourianova.apptrainer.exception.DaoException;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.Order;
import by.gourianova.apptrainer.entity.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDao extends AbstractDao<Order> {

    private final static String SQL_FIND_ORDER = "SELECT * FROM orders WHERE Users_Id=? ORDER BY id DESC LIMIT 1;";
    private final static String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders;";
    private final static String SQL_FIND_UNCLOSED_ORDERS = "SELECT * FROM orders WHERE End_Date IS NULL;";
    private final static String SQL_FIND_UNCLOSED_USER_ORDER = "SELECT * FROM orders WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_FIND_ALL_USER_ORDERS = "SELECT * FROM orders WHERE Users_Id=?;";
    private final static String SQL_CLOSE_ORDER = "UPDATE orders SET End_Date=now(), Value=? WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_UPDATE_USER = "UPDATE users SET Balance=?, Roles_Id=2 WHERE Id=?;";
    private final static String SQL_UPDATE_APP = "UPDATE apps SET In_Rent=0 WHERE Id=?;";

    @Override
    public ArrayList<Order> findAll() throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    @Override
    public Order findEntityById(Integer userId) throws DaoException {
        Order order = new Order();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ORDER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    @Override
    public boolean createEntity(Order entity) throws DaoException {

        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        return false;
    }


    public Order findUnclosedOrder(Integer userId) throws DaoException {
        Order order = new Order();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_USER_ORDER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                order.setUserId(resultSet.getInt(5));
                order.setAppId(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosedOrder method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    public ArrayList<Order> findAllUserOrders(Integer userId) throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER_ORDERS);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllUserOrders method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    public ArrayList<Order> findUnclosed() throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosed method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    public void closeOrder(User user, BigDecimal value, App app) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_CLOSE_ORDER);
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
                throw new DaoException("Error in closeOrder method", e);
            }
            throw new DaoException("Error in closeOrder method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(1));
        order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
        if (!(resultSet.getTimestamp(3) == null)) {
            order.setEndRent(resultSet.getTimestamp(3).toLocalDateTime());
        }
        order.setValue(resultSet.getBigDecimal(4));
        order.setUserId(resultSet.getInt(5));
        order.setAppId(resultSet.getInt(6));
        return order;
    }
}
