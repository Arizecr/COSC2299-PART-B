package test;

import coreFunctions.Driver;
import menu.BusinessMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by yesmi on 25/03/2017.
 */
public class WorkingTimesTesting {
    Driver driver = new Driver();
    String firstdate;//(dd/mm/yyyy)
    String firsttime;//(hh:mm:ss)
    String endtime;//(hh:mm:ss)
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

    @Test
    public void correctWorkingTimesAM() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        firstdate = date.format(c.getTime());
        firsttime = "00:00:00";
        endtime = "03:30:00";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertFalse(verify);

    }
    @Test
    public void correctWorkingTimesPM() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        firstdate = date.format(c.getTime());
        firsttime = "13:00:00";
        endtime = "19:30:00";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertFalse(verify);

    }
    @Test
    public void correctWorkingTimesMidday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        firstdate = date.format(c.getTime());
        firsttime = "10:00:00";
        endtime = "12:30:00";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertFalse(verify);

    }

    @Test
    public void AllNullWorkingTimes() {
        firstdate = "";
        firsttime = "";
        endtime = "";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void NullTimeWorkingTimes() {
        firstdate = "12/12/2017";
        firsttime = "";
        endtime = "";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }

    @Test
    public void NullDateWorkingTimes() {
        firstdate = "";
        firsttime = "01:30:00";
        endtime = "08:30:00";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void NullStartTimeWorkingTimes() {
        firstdate = "01/05/2017";
        firsttime = "";
        endtime = "08:30:00";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void NullStartAndDateTimeWorkingTimes() {
        firstdate = "";
        firsttime = "";
        endtime = "08:30:00";
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void CurrentTimeWorkingTimes() {
        Date currDate =  new Date();
        Calendar c = Calendar.getInstance();
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(currDate);
        firsttime = time.format(currDate);
        c.add(Calendar.HOUR,3);
        endtime = c.getTime().toString();
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }

    @Test
    public void YesterdayWorkingTimes() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,-1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,2);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void TomorrowWorkingTimes() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,2);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertFalse(verify);

    }
    @Test//length too short
    public void WorkingTimes10MIN() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.MINUTE,10);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test//length too long
    public void WorkingTimesOver8HOURs() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,8);
        endtime = time.format(c.getTime());
        c.add(Calendar.MINUTE,1);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test//length too long
    public void WorkingTimesOver8HOURs2() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,8);
        endtime = time.format(c.getTime());
        c.add(Calendar.MINUTE,2);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test//length too long
    public void WorkingTimesOver8HOURs3() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,8);
        endtime = time.format(c.getTime());
        c.add(Calendar.MINUTE,3);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test//length too long
    public void WorkingTimesExact8HOURs() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,8);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertFalse(verify);

    }
    @Test//length too long
    public void WorkingTimesApprox8HOURs() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,7);
        c.add(Calendar.MINUTE,59);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertFalse(verify);

    }
    @Test//length too long
    public void WorkingTimesStartAfterEnd() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,-8);
        endtime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,endtime);
        assertTrue(verify);

    }
    @Test//length too long
    public void WorkingTimesStartEqualsEnd() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        firstdate = date.format(c.getTime());
        firsttime = time.format(c.getTime());
        verify = b.Workt(firstdate, firsttime,firsttime);
        assertTrue(verify);

    }


}