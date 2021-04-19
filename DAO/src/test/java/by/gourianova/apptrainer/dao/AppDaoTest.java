package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.DaoException;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class AppDaoTest {
    @Test
    public void findEntityById(){
        AppDao appDao=new AppDao();
        App app=new App();
        try {
            app=appDao.findEntityById(new Integer(74));
            assertEquals("FindTheWay",app.getTitle());
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
