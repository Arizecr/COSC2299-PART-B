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
    public void nullStartTimes()  {
        w.Details();
        day = "monday";
        starttime = "";
        endtime = "20:00";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void nulldEndTimes()  {
        w.Details();
        day = "monday";
        starttime = "2:00";
        endtime = "";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void nullStartEndTimes()  {
        w.Details();
        day = "monday";
        starttime = "";
        endtime = "";
        verify = w.readWork(bId, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void allNull(){
        verify = w.readWork("", "", "", "");
        assertTrue(verify);

    }

    @Test
    public void nullDay(){
        starttime = "8:00";
        endtime = "19:00";
        verify = w.readWork(bId, "", starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void nullDayandStart(){
        starttime = "";
        endtime = "19:00";
        verify = w.readWork(bId, "", starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void nullDayandEnd(){
        starttime = "8:00";
        endtime = "";
        verify = w.readWork(bId, "", starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidDay(){
        starttime = "8:00";
        endtime = "";
        verify = w.readWork(bId, "dwadwadwadwasadsfafad", starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidStart(){
        starttime = "aaaaaaaa";
        endtime = "19:00";
        verify = w.readWork(bId, "monday", starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidStartEnd(){
        starttime = "aaaaaaaa";
        endtime = "aaaaaabbbaa";
        verify = w.readWork(bId, "monday", starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEnd(){
        starttime = "8:00";
        endtime = "aaaaaabbbaa";
        verify = w.readWork(bId, "monday", starttime, endtime);
        assertTrue(verify);

    }





}