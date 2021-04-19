package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.db.ConnectionPool;
import by.gourianova.apptrainer.db.ProxyConnection;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.DaoException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.apache.logging.log4j.core.util.Closer.close;

public class UserDao extends AbstractDao<User> {

    private final static String SQL_CREATE_TABLE_USERS = "create table if not exists users(id INT(11) NOT NULL auto_increment," +
            "Login varchar(10) not null unique , Password varchar(32) not null,First_Name varchar(15) not null, " +
            "Last_Name varchar(15) not null, Balance DECIMAL (6,2), Roles_id INT(11), Create_time DATETIME,  primary key (id) ) DEFAULT CHARSET=utf8;";
    private final static String SQL_CREATE_USER = "INSERT INTO users (Login, Password, First_Name, Last_Name, Balance, Roles_Id,  Create_time) VALUES (?, ?, ?, ?,?,?,?);";
    private final static String SQL_FIND_ALL_USERS = "SELECT * FROM users;";
    private final static String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private final static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";
    private final static String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
    private final static String SQL_UPDATE_BALANCE = "UPDATE users SET Balance=? WHERE Id=?;";
    private final static String SQL_UPDATE_ROLE = "UPDATE users SET Roles_Id=? WHERE Id=?;";
    private final static String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?;";
    private final static String SQL_UPDATE_PASSWORD = "UPDATE users SET Login=?, Password=?  WHERE Id=?;";

    public void setSqlCreateTableUsers() {
        Statement statement = null;
        ProxyConnection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQL_CREATE_TABLE_USERS);
        } catch (SQLException e) {

            try {
                throw new DaoException("Error in setSqlCreateTableUsers method", e);
            } catch (DaoException daoException) {
                //log.
                System.out.println(" couldn't throw DaoException in UserDao.setSqlCreateTableUser");
                daoException.printStackTrace();
            }

        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public ArrayList<User> findAll() throws DaoException {
        ArrayList<User> usersList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return usersList;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public boolean createEntity(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setBigDecimal(5, new BigDecimal(String.valueOf(user.getBalance())));
            preparedStatement.setInt(6, new Integer(6));
            LocalDate date = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            preparedStatement.setDate(7, sqlDate);
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreate;
    }

    @Override
    public boolean deleteEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        boolean isDeleted = false;
        PreparedStatement preparedStatement = null;
        connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
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


    public User findEntityByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByLoginAndPassword method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    public boolean findEntityByLogin(String login) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByLogin method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public User updateBalance(User user, BigDecimal newBalance) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE);
            preparedStatement.setBigDecimal(1, user.getBalance().add(newBalance));
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            user.setBalance(user.getBalance().add(newBalance));
        } catch (SQLException e) {
            throw new DaoException("Error in updateBalance method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    public User updateEntity(User user) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE);
            preparedStatement.setInt(1, user.getRoleId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
   return user; }

    public User updateUserPassword(User user, String password) throws DaoException {
        ProxyConnection connection = null;
        boolean isAllowed = false;
        boolean isUpdated = false;
        PreparedStatement preparedStatement = null;
       /* User user = null;
        try {
            user = this.authorization(userloginInfo.getLogin(), userloginInfo.getPassword());
            if (user == null) isAllowed = true;
            log.println("login&password are uniq");
        } catch (Exception e) {
            log.println("Smth with proof of uniq login and password");
            e.printStackTrace();
        }
        if (isAllowed) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(SQL_UPDATE_PASSWORD);
                log.println(userloginInfo.getLogin() + " userloginInfo.getLogin() " + userloginInfo.getPassword() + " userloginInfo.getPassword() " + userloginInfo.getId() + " userloginInfo.getId() ");
                preparedStatement.setString(1, userloginInfo.getLogin());
                preparedStatement.setString(2, userloginInfo.getPassword());
                if (userloginInfo.getId() != 0) {
                    preparedStatement.setInt(3, userloginInfo.getId());
                } else {
         //           isUpdated = false;
         //           log.println("0 id");
                    return null;
                }
                preparedStatement.executeUpdate();
       //         isUpdated = true;
       //        log.println("SQLUserDAO.updateData OK ");

            } catch (SQLException e) {
                throw new DaoException("Error in updateUserPassword method", e);
            } finally {
                close(preparedStatement);
                close(connection);
            }
        }
        return updatedUser;*/
return null;    }


    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setFirstName(resultSet.getString(4));
        user.setLastName(resultSet.getString(5));
        user.setBalance(resultSet.getBigDecimal(6));
        user.setRoleId(resultSet.getInt(7));
        //TODO: FIX
        //                date = resultSet.getDate("Create_time").toLocalDate();
        //               if (date == null) date = LocalDate.now();
        //user.setCreate_time(resultSet.getDate(8));
        return user;
    }


  /*  public boolean isRegistrationAllowed(User user) {
        boolean isAllowed = false;
        User user = null;
        try {
            user = this.authorization(registrationInfo.getLogin(), registrationInfo.getPassword());
            if (user == null) isAllowed = true;
        } catch (Exception e) {
            log.println("Smth with proof of uniq login and password");
            e.printStackTrace();
        }
        if (Integer.parseInt(registrationInfo.getBalance()) < 0) {
            isAllowed = false;
            log.println("Balance should be positive");
        }
        return isAllowed;
    }*/
}

