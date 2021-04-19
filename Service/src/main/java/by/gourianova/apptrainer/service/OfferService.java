package by.gourianova.apptrainer.service;

import by.gourianova.apptrainer.dao.AppDao;
import by.gourianova.apptrainer.dao.OfferDao;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.Offer;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.DaoException;
import by.gourianova.apptrainer.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OfferService {
    private OfferDao offerDao = new OfferDao();
    private AppDao appDao = new AppDao();


      public Offer findEntityById(Integer id) throws ServiceException {
        try {
            return offerDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findEntityById method", e);
        }
    }

    public ArrayList<Offer> findAllUserOffers(Integer userId) throws ServiceException {
        try {
            return offerDao.findAllUserOffers(userId);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllUserOffers method", e);
        }
    }

    public ArrayList<Offer> findAll() throws ServiceException {
        try {
            return offerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }


}
