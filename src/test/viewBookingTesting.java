package test;

import coreFunctions.Driver;
import menu.CustomerMenu;
import menu.Login;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by yesmi on 8/04/2017.
 */
public class viewBookingTesting {
    Driver d =new Driver();
    Login login = new Login();
    CustomerMenu cm = new CustomerMenu();
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
    @Test (timeout =200)
    public void customerAvailableBooking(){

        login.getOwnerinfo();
        cm.availableBookings(0);
    }
    @Test
    public void verifyBusiness(){
        login.getOwnerinfo();
        int count = 1;
        //gets all the names of all business's registered to the system
        for(int i=0;i<login.businessList.size();i++){
             count++;
            System.out.println(count);
        }

        boolean check = cm.verifyBusinessID(1, count);
        assertTrue(check);
    }

    @Test
    public void verifyBusiness2(){
        login.getOwnerinfo();
        int count = 0;
        //gets all the names of all business's registered to the system
        for(int i=1;i<login.businessList.size();i++){
            count++;
        }
        boolean check = cm.verifyBusinessID(3, count);
        assertFalse(check);
    }
    @Test
    public void verifyBusiness3(){
        login.getOwnerinfo();
        int count = 0;
        //gets all the names of all business's registered to the system
        for(int i=1;i<login.businessList.size();i++){
            count++;
        }
        boolean check = cm.verifyBusinessID(0, count);
        assertFalse(check);
    }


    @Test (expected = IndexOutOfBoundsException.class)
    public void customerAvailableBooking2(){
        Login login = new Login();
        login.getOwnerinfo();
        cm.availableBookings(9);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void customerAvailableBooking3(){
        Login login = new Login();
        login.getOwnerinfo();
        cm.availableBookings(22222222);
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