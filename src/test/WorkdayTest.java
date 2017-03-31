package test;

import BusinessWorkDays.Workday;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martin on 31/03/2017.
 */
public class WorkdayTest {

    String bId = "b1";
    String starttime;//(hh:mm)
    String endtime;//(hh:mm)
    String day;
    boolean verify;
    Workday w = new Workday();


    @Test
    public void withinBusinessOperation()  {
        w.Details();
        day = "monday";
        starttime = "8:00";
        endtime = "19:00";
        verify = w.readWork(bId, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void startingBeforeBusinessOperations()  {
        w.Details();
        day = "monday";
        starttime = "0:00";
        endtime = "7:00";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void endingAfterBusinessOperations()  {
        w.Details();
        day = "monday";
        starttime = "2:00";
        endtime = "20:00";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidStartTimes()  {
        w.Details();
        day = "monday";
        starttime = "";
        endtime = "20:00";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEndTimes()  {
        w.Details();
        day = "monday";
        starttime = "2:00";
        endtime = "";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }



}