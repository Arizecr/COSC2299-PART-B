/*** NOTE: this testing is for an unused function in our code.
**/
package test;

import EmployeeAvailabilityDays.AvailableDay;
import coreFunctions.WriteToFile;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void validEmployeeAvailability()  {
        e.loadInfo("b1");
        day = "monday";
        starttime = "3:00";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void invalidEmployeeStartTimeAfterBusinessHours()  {
        e.loadInfo("b1");
        day = "monday";
        starttime = "20:00";
        endtime = "22:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEmployeeStartTimeBeforeBusinessHours()  {
        e.loadInfo("b1");
        day = "monday";
        starttime = "0:00";
        endtime = "5:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Test
    public void invalidEmployeeStartTimeBeforeAfterBusinessHours()  {
        e.loadInfo("b1");
        day = "monday";
        starttime = "0:00";
        endtime = "24:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

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

    @Ignore
    public void nullTime()  {

        day = "";
        emp = "";
        starttime = "";
        endtime = "";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Ignore
    public void allNull()  {
        bId = "";
        day = "";
        emp = "";
        starttime = "";
        endtime = "";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Ignore
    public void invalidDay()  {

        day = "dasdadad";
        starttime = "3:00";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Ignore
    public void invalidStartTime()  {

        day = "monday";
        starttime = "wadwadd";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Ignore
    public void invalidEndTime()  {

        day = "monday";
        starttime = "3:00";
        endtime = "dadad";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }
    //Should Return True
    @Ignore
    public void invalidEmployeeAvailability()  {
        e.loadInfo("b1");
        day = "monday";
        starttime = "3:00";
        endtime = "2:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }

    @Ignore
    public void nullDay()  {

        day = "";
        starttime = "0:00";
        endtime = "7:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

}