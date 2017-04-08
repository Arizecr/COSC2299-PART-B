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
        d.loadCurrentBookings();
        d.loadPastBookings();
    }

    @After
    public void tearDown() throws Exception {

    }
//--------customer booking
    @Test (timeout =100)
    public void customerBookings() {
        d.viewBookingsCustomer("c1");
    }
    @Test (timeout =100)
    public void customerBookings2(){
        d.viewBookingsCustomer("c2");
    }
//---------business owner
    @Test(timeout =100)
    public void PastBookings() {
        d.viewPastBookings();
    }

    @Test (timeout =100)
    public void viewCurrentBookings(){
        d.viewCurrentBookings();
    }

}