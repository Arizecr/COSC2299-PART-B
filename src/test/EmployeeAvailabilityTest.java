package test;

import EmployeeAvailabilityDays.AvailableDay;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martin on 31/03/2017.
 */
public class EmployeeAvailabilityTest {

    String bId = "b1";
    String emp = "e1";
    String starttime;//(hh:mm)
    String endtime;//(hh:mm)
    String day;
    boolean verify;
    AvailableDay e = new AvailableDay();

    @Test
    public void validEmployeeAvailability()  {
        e.loadInfo();
        day = "monday";
        starttime = "3:00";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    //Should Return True
    @Test
    public void invalidEmployeeAvailability()  {
        e.loadInfo();
        day = "monday";
        starttime = "3:00";
        endtime = "2:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEmployeeStartTimeAfterBusinessHours()  {
        e.loadInfo();
        day = "monday";
        starttime = "20:00";
        endtime = "22:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEmployeeStartTimeBeforeBusinessHours()  {
        e.loadInfo();
        day = "monday";
        starttime = "0:00";
        endtime = "5:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEmployeeStartTimeBeforeAfterBusinessHours()  {
        e.loadInfo();
        day = "monday";
        starttime = "0:00";
        endtime = "24:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void nullDay()  {

        day = "";
        starttime = "0:00";
        endtime = "7:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void nullEmployeeID()  {

        day = "monday";
        emp = "e1";
        starttime = "2:00";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void nullTime()  {

        day = "";
        emp = "";
        starttime = "";
        endtime = "";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void allNull()  {
        bId = "";
        day = "";
        emp = "";
        starttime = "";
        endtime = "";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void invalidDay()  {

        day = "dasdadad";
        starttime = "3:00";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void invalidStartTime()  {

        day = "monday";
        starttime = "wadwadd";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void invalidEndTime()  {

        day = "monday";
        starttime = "3:00";
        endtime = "dadad";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }


}