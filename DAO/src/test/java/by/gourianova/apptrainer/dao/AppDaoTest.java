package by.gourianova.apptrainer.dao;

import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.DaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

//import static org.junit.Assert.assertEquals;

//@RunWith(value= Parameterized.class)
public class AppDaoTest {
  /*  private int app_Id;
    private String expectedTitle;

 @Parameters
    public static Collection<Object[]> multipleTableVales( ) {
        return Arrays.asList(new Object[][]{

                {74,"FindTheWay"},
                {75,"BinocularvisionPlatformer"},
                {76,  "GleamyStar"},
                {77,"TextBook"},
                {78, "BinocularvisionArcada"},
                {79,"BinocularvisionArcada"}



        });
    }
    public  AppDaoTest(int apps_Id, String expectedTitles){
     this.app_Id=apps_Id;
     this.expectedTitle=expectedTitles;

    }
    @Test
    public void findAppByIdTest001(){
        String actualTitle=null;
        AppDao appDao=new AppDao();
        try {

          App  app=appDao.findEntityById(app_Id);
            actualTitle=app.getTitle();
            Assert.assertEquals(expectedTitle, actualTitle);
                    //assertEquals("FindTheWay",app.getTitle());
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

   */
}
