package test;

import coreFunctions.Driver;
import org.junit.*;
import menu.BusinessMenu;
import static org.junit.Assert.*;


/**
 * Created by yesmi on 25/03/2017.
 */
public class WorkingTimesTesting {
    Driver driver = new Driver();
    String firstdate;
    String firsttime;
    String endtime;
    BusinessMenu b = new BusinessMenu();
    boolean verify;
    @BeforeClass
    public static void loadDriver(){
      /*  Login log = new Login();

        log.loadCustomerInformation();
        log.getOwnerinfo();*/
    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test // tfn and phone no should be restricted to a specific format
    public void correctWorkingTimes() {
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }


}