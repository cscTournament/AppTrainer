package by.gourianova.apptrainer.service;

import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.dao.AppDao;
import by.gourianova.apptrainer.dao.OrderDao;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.Order;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.DaoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private AppDao appDao = new AppDao();

    public boolean checkBalance(User user) throws ServiceException {
        BigDecimal value = calculateOrder(user);
        return user.getBalance().intValue() >= value.intValue();
    }

    public BigDecimal calculateOrder(User user) throws ServiceException {
        Order order;
        App app;
        BigDecimal value;
        try {
            order = orderDao.findUnclosedOrder(user.getId());
            LocalDateTime startRent = order.getStartRent();
            LocalDateTime endRent = LocalDateTime.now();
            BigDecimal minutes = new BigDecimal(ChronoUnit.MINUTES.between(startRent, endRent));
            app = appDao.findEntityById(order.getAppId());
            BigDecimal pricePerMinutes = app.getPricePerHour();
            value = minutes.multiply(pricePerMinutes);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in calculateOrder method", e);
        }
        return value;
    }

    public void closeOrder(User user) throws ServiceException {
        Order order;
        App app;
        try {
            order = orderDao.findUnclosedOrder(user.getId());
            LocalDateTime startRent = order.getStartRent();
            LocalDateTime endRent = LocalDateTime.now();
            BigDecimal minutes = new BigDecimal(ChronoUnit.MINUTES.between(startRent, endRent));
            app = appDao.findEntityById(order.getAppId());
            BigDecimal pricePerMinutes = app.getPricePerHour();
            BigDecimal value = minutes.multiply(pricePerMinutes);
            orderDao.closeOrder(user, value, app);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in closeOrder method", e);
        }
    }

    public Order findEntityById(Integer id) throws ServiceException {
        try {
            return orderDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findEntityById method", e);
        }
    }

    public ArrayList<Order> findAllUserOrders(Integer userId) throws ServiceException {
        try {
            return orderDao.findAllUserOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllUserOrders method", e);
        }
    }

    public ArrayList<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public ArrayList<Order> findUnclosed() throws ServiceException {
        try {
            return orderDao.findUnclosed();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findUnclosed method", e);
        }
    }
}
