package test;

import bookings.Services;
import coreFunctions.Driver;
import menu.BusinessMenu;
import menu.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by yesmi on 30/04/2017.
 */
public class CustomerBookingTest
{
    Login loginMenu = new Login();
    Services s = new Services();
    Login login = new Login();
    Driver driver = new Driver();
    BusinessMenu bm = new BusinessMenu();
    String businessID = "b1";
    String startinfo = "12:00";
    String endinfo = "12:00";
    Date start;
    String day = "Monday";


    @BeforeClass
    public static void loadUsers(){//test data initialised


    }

    @Test
    public void validBooking()  {
        boolean toAssert = bm.UserBooking(businessID, day, startinfo, endinfo)||driver.checkCurrBookings(businessID, start, startinfo, endinfo);
    }
}