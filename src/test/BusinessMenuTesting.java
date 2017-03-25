package test;

import coreFunctions.Driver;
import menu.Login;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by yesmi on 25/03/2017.
 */
public class BusinessMenuTesting {
    @BeforeClass
    public static void loadDriver(){
        Login log = new Login();
        Driver driver = new Driver();
        log.loadCustomerInformation();
        log.getOwnerinfo();
    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void verifyLoginAll() {
    }

}