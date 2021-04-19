package by.gourianova.apptrainer.service;

import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.dao.HttpAddressDao;
import by.gourianova.apptrainer.entity.Order;
import by.gourianova.apptrainer.entity.Role;
import by.gourianova.apptrainer.entity.HttpAddress;
import by.gourianova.apptrainer.exception.DaoException;

import java.util.ArrayList;

public class HttpAddressService {

    private HttpAddressDao httpAddressDao = new HttpAddressDao();

    public ArrayList<HttpAddress> findAll() throws ServiceException {
        try {
            return httpAddressDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public boolean createHttpAddress(HttpAddress httpAddress) throws ServiceException {
        try {
            return httpAddressDao.createEntity(httpAddress);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createStation method", e);
        }
    }
}
