package test;

import coreFunctions.Driver;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by yesmi on 8/04/2017.
 */
public class viewBookingTesting {
    Driver d =new Driver();
    public static ArrayList<String> check1 = new ArrayList<>();
    public static ArrayList<String> check2 = new ArrayList<>();
    public static ArrayList<String> check3 = new ArrayList<>();
    public static ArrayList<String> expected = new ArrayList<>();
    @Before
    public void setUp()  {
        d.loadCurrentBookings();
        d.loadPastBookings();
        expected.clear();
        check1.clear();
        expected.add("Monday");
        expected.add("Monday");
        expected.add("Tuesday");
        expected.add("Tuesday");
        expected.add("Friday");
        expected.add("Sunday");

    }
    // TESTING OF VOID PRINTING METHODS time is no more than 100 milis
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

    //-------sorting function check with random data
    @Test
    public void constructData1()  {
        check1.add("Monday");
        check1.add("Tuesday");
        check1.add("Monday");
        check1.add("Friday");
        check1.add("Sunday");
        check1.add("Tuesday");
        assertEquals(d.insertionSort(check1),expected);
    }
    @Test
    public void constructData2()  {
        check2.clear();
        check2.add("Monday");
        check2.add("Sunday");
        check2.add("Tuesday");
        check2.add("Tuesday");
        check2.add("Monday");
        check2.add("Sunday");
        check2.add("Friday");
        expected.add("Sunday");
        assertEquals(d.insertionSort(check2),expected);
    }
    @Test
    public void constructData3Error()  {
        check2.add("Monday");
        assertFalse(d.insertionSort(check2).equals(expected));
    }
}