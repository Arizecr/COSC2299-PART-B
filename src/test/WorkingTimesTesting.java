package test;

import coreFunctions.Driver;
import coreFunctions.WriteToFile;
import menu.BusinessMenu;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by yesmi on Monday.
 */
public class WorkingTimesTesting {
    Driver driver = new Driver();
    String day;//(EEEE)
    String firsttime;//(hh:mm)
    String endtime;//(hh:mm)
    BusinessMenu b = new BusinessMenu();
    boolean verify;
    String bId = "b1";
    String empId ="e1";
    @BeforeClass
    public static void loadUsers(){//test data initialised
        WriteToFile w = new WriteToFile();
        w.reWriteToWorkingdayTXT("", "workdaysList.txt");
        w.reWriteToWorkingdayTXT("b1 monday 9:00 17:00", "businessdaysList.txt");
        w.WriteToWorkingdayTXT("b1 tuesday 9:00 17:00", "businessdaysList.txt");
        w.WriteToWorkingdayTXT("b1 wednesday 9:00 17:00", "businessdaysList.txt");
        w.WriteToWorkingdayTXT("b1 friday 9:00 17:00", "businessdaysList.txt");
        w.WriteToWorkingdayTXT("b1 thursday 9:00 17:00", "businessdaysList.txt");
        w.reWriteToWorkingdayTXT("b1 e1 Friday 00:00 20:00", "employeeAvailabilityList.txt");
        w.WriteToWorkingdayTXT("b1 e1 Monday 2:00 19:00", "employeeAvailabilityList.txt");
        w.WriteToWorkingdayTXT("b1 e1 Tuesday 2:00 19:00", "employeeAvailabilityList.txt");

    }


    @Test
    public void correctWorkingTimesPMOnSameDay() {
        day = "monday";
        firsttime = "15:00";
        endtime = "16:00";
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertFalse(verify);

    }
    @Test
    public void correctWorkingTimesMidday() {
        day ="tuesday";
        firsttime = "10:00";
        endtime = "12:30";
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertFalse(verify);

    }

    @Test
    public void AllNullWorkingTimes() {
        day = "";
        firsttime = "";
        endtime = "";
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void NullTimeWorkingTimes() {
        day = "Monday";
        firsttime = "";
        endtime = "";
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertTrue(verify);

    }

    @Test
    public void NullDayWorkingTimes() {
        day = "";
        verify = b.checkD(day);
        assertTrue(verify);

    }

    @Test
    public void InvalidDayWorkingTimes() {
        day = "lodwkaokdo";
        verify = b.checkD(day);
        assertTrue(verify);

    }

    @Test
    public void validDayWorkingTimes() {
        day = "monday";
        verify = b.checkD(day);
        assertFalse(verify);

    }

    @Test
    public void NullStartTimeWorkingTimes() {
        day = "Monday";
        firsttime = "";
        endtime = "08:30";
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertTrue(verify);

    }
    @Test
    public void NullStartAndDayTimeWorkingTimes() {
        day = "";
        firsttime = "";
        endtime = "08:30";
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertTrue(verify);

    }


    @Test//length too long
    public void WorkingTimesStartAfterEnd() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        DateFormat time = new SimpleDateFormat("HH:mm");
        day = "friday";
        firsttime = time.format(c.getTime());
        c.add(Calendar.HOUR,-8);
        endtime = time.format(c.getTime());
        verify = b.Workt(bId,empId, day, firsttime,endtime);
        assertTrue(verify);

    }
    @Test//length too long
    public void WorkingTimesStartEqualsEnd() {

        day = "Tuesday";
        firsttime = "12:00";
        verify = b.Workt(bId,empId, day, firsttime,firsttime);
        assertTrue(verify);

    }


}