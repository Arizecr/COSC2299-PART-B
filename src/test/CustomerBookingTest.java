package test;

import Gui.customerMenu.bookingController;
import bookings.Services;
import coreFunctions.Driver;
import menu.BusinessMenu;
import menu.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    String endinfo = "12:30";
    Date start;
    String day = "Monday";
    String dateinfo;


    @BeforeClass
    public static void loadDate(){//test data initialised

    }
    //--------------------------------------------check that booking is within business hours , employees have shifts during these times and no bookings during these times
    @Test
    public void validBooking() {
       dateinfo = "12/04/2017";
       setTime(dateinfo);
        boolean toAssert = bm.UserBooking(businessID, day, startinfo, endinfo)||driver.checkCurrBookings(businessID, start, startinfo, endinfo);
        assertFalse(toAssert);
    }
    @Test
    public void invalidBookingDay() {
        dateinfo = "30/04/2017";
        setTime(dateinfo);
        boolean toAssert = bm.UserBooking(businessID, day, startinfo, endinfo)||driver.checkCurrBookings(businessID, start, startinfo, endinfo);
        assertTrue(toAssert);
    }
    @Test
    public void invalidBookingDay2() {
        dateinfo = "29/04/2017";
        setTime(dateinfo);
        boolean toAssert = bm.UserBooking(businessID, day, startinfo, endinfo)||driver.checkCurrBookings(businessID, start, startinfo, endinfo);
        assertTrue(toAssert);
    }
    //---------------------------------------------------------------------------check dates are correct
    bookingController bc = new bookingController();
    @Test
    public void invalidDate() {
        dateinfo = "29/04/2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateinfo, formatter);
        bc.setDateinfo(localDate);
        assertTrue(bc.dateCheck());
    }
    @Test
    public void invalidDateNextYear() {
        dateinfo = "30/04/2018";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateinfo, formatter);
        bc.setDateinfo(localDate);
        assertTrue(bc.dateCheck());
    }
    @Test
    public void invalidLastYear() {
        dateinfo = "29/04/2016";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateinfo, formatter);
        bc.setDateinfo(localDate);
        assertTrue(bc.dateCheck());
    }


    @Test
    public void validDate() {
        start = Calendar.getInstance().getTime();

        DateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        dateinfo = time.format(start);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateinfo, formatter);

        bc.setDateinfo(localDate);
        assertFalse(bc.dateCheck());
    }
    @Test
    public void validDateUppBound() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +30);
        start = calendar.getTime();

        DateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        dateinfo = time.format(start);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateinfo, formatter);

        bc.setDateinfo(localDate);
        assertFalse(bc.dateCheck());
    }
    @Test
    public void invalidDateUppBound() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +31);
        start = calendar.getTime();

        DateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        dateinfo = time.format(start);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateinfo, formatter);

        bc.setDateinfo(localDate);
        assertTrue(bc.dateCheck());
    }
    //------------------------------------------------------------------------starttime check valid
    @Test
    public void validStart() {
        startinfo = "11:30";
        boolean toAssert =     (startinfo ==null)||bm.checktime(startinfo);
        assertFalse(toAssert);
    }
    @Test
    public void NullStart() {
        startinfo = "";
        boolean toAssert =     (startinfo ==null)||bm.checktime(startinfo);
        assertTrue(toAssert);
    }
    @Test
    public void invalidStart() {
        startinfo = "5r6ty";
        boolean toAssert =     (startinfo ==null)||bm.checktime(startinfo);
        assertTrue(toAssert);
    }
    @Test
    public void invalidStart2() {
        startinfo = "1111111111111:6555555555555555";
        boolean toAssert =     (startinfo ==null)||bm.checktime(startinfo);
        assertTrue(toAssert);
    }

    @Test
    public void invalidStart3() {
        startinfo = "11:6555555555555555";
        boolean toAssert =     (startinfo ==null)||bm.checktime(startinfo);
        assertTrue(toAssert);
    }
    @Test
    public void invalidStart4() {
        startinfo = "1:5";
        boolean toAssert =     (startinfo ==null)||bm.checktime(startinfo);
        assertTrue(toAssert);
    }

    //dummy method
   public void  setTime(String dateinfo){
       DateFormat time = new SimpleDateFormat("dd/MM/yyyy");
       SimpleDateFormat d = new SimpleDateFormat("EEEE");
       DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       start = Calendar.getInstance().getTime();

       // System.out.println(startinfo + " " + endinfo);
       try {
           start = time.parse(dateinfo);
       } catch (ParseException e) {
       }
       day = d.format(start);

   }

}