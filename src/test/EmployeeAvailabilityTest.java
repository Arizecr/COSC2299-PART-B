package test;

import EmployeeAvailabilityDays.AvailableDay;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martin on 31/03/2017.
 */
public class EmployeeAvailabilityTest {

    String bId = "b1";
    String emp;
    String starttime;//(hh:mm)
    String endtime;//(hh:mm)
    String day;
    boolean verify;
    AvailableDay e = new AvailableDay();

    @Test
    public void noEmployee()  {

        day = "monday";
        starttime = "8:00";
        endtime = "19:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertFalse(verify);

    }

    @Test
    public void withEmployeeWithinFile()  {

        day = "monday";
        emp = "e1";
        starttime = "8:00";
        endtime = "13:00";
        verify = e.checkFile(bId, emp, day, starttime, endtime);
        assertTrue(verify);

    }


}