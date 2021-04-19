package by.gourianova.apptrainer.service;

import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.dao.AppTypeDao;
import by.gourianova.apptrainer.entity.AppType;
import by.gourianova.apptrainer.exception.DaoException;

import java.io.InputStream;
import java.util.ArrayList;


public class AppTypeService {
    private AppTypeDao appTypeDAO = new AppTypeDao();

    public ArrayList<AppType> findAll() throws ServiceException {
        ArrayList<AppType> typesList;
        try {
            typesList = appTypeDAO.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
        return typesList;
    }

    public void editPrice(AppType appType) throws ServiceException {
        try {
            appTypeDAO.updateEntity(appType);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in editPrice method", e);
        }
    }

    public void create(AppType appType, InputStream inputStream) throws ServiceException {
        try {
            appTypeDAO.create(appType, inputStream);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in create method", e);
        }
    }
}
