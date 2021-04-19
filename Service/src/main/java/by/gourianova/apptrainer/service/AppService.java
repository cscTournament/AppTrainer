package by.gourianova.apptrainer.service;

import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.util.MailSender;
import by.gourianova.apptrainer.dao.AppDao;
import by.gourianova.apptrainer.dao.HttpAddressDao;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.exception.DaoException;

import java.util.ArrayList;


public class AppService {

    private final static Integer MIN_NUMBER_APP_ON_HTTPADDRESS = 100000000;
    private final static String MAIL_TO = "agu_agu@tut.by";
    private final static String MAIL_SUBJECT = "Warning: RentApp";
    private final static String MAIL_TEXT = "The technical characteristics of connection is not allow to run the app";

    private AppDao appDao = new AppDao();

    public ArrayList<App> findAll() throws ServiceException {
        ArrayList<App> appsList;
        try {
            appsList = appDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
        return appsList;
    }

    public ArrayList<App> findAllByPage(int pageCapacity, int pageNumber) throws ServiceException {
        ArrayList<App> appsList;
        try {
            appsList = appDao.findAllByPage(pageCapacity, pageNumber);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllByPage method", e);
        }
        return appsList;
    }

    public App rentApp(Integer appId, Integer userId) throws ServiceException {
        App app;
        try {
            app = appDao.rentApp(appId, userId);
            Integer httpAddressId = app.getHttpAddressesId();
           
            if (checkNumberOfApps(httpAddressId)) {
               MailSender.send(MAIL_SUBJECT, MAIL_TEXT, MAIL_TO);
           }
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in rentApp method", e);
        }
        return app;
    }

    public boolean createApp(App app) throws ServiceException {
        try {
            return appDao.createEntity(app);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createApp method", e);
        }
    }
//TODO: check:not nessasary? depends of how much users could run one app a one time if it's in cash, not on server it's not nessasary
    private boolean checkNumberOfApps(Integer httpAddressId) throws DaoException {
        HttpAddressDao httpAddressDao = new HttpAddressDao();

         int appsCount = httpAddressDao.checkNumberAppOnHttpAddress(httpAddressId);
        return appsCount < MIN_NUMBER_APP_ON_HTTPADDRESS;
    }
}
