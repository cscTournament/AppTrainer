package by.gourianova.apptrainer.service;

import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.dao.AppDao;
import by.gourianova.apptrainer.dao.HttpAddressDao;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.exception.DaoException;

import java.util.ArrayList;


public class AppService {

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
            String url=app.getUrl();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in rentApp method", e);
        }
        return app;
    }

    public boolean createApp(App app) throws ServiceException {
        try {
            System.out.println("try AppService.createApp  ");
            return appDao.createEntity(app);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createApp method", e);
        }
    }
}
