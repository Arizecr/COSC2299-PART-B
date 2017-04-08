package test;

import coreFunctions.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yesmi on 8/04/2017.
 */
public class viewBookingTesting {
    Driver d =new Driver();
    @Before
    public void setUp()  {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test (timeout =100)
    public void customerBookings() throws Exception {
        d.viewBookingsCustomer("c1");
    }

    @Test
    public void loadPastBookings() throws Exception {

    }

    @Test
    public void viewBookingsCustomer() throws Exception {

    }

    @Test
    public void viewBookings() throws Exception {

    }

}