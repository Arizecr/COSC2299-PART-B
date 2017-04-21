package test;

import bookings.Services;
import coreFunctions.WriteToFile;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yesmi on 21/04/2017.
 */
public class ServicesTesting {
    Services s = new Services();
    @BeforeClass
    public static void setUp() {
        //sample data used during testing
        WriteToFile w = new WriteToFile();
        Services s = new Services();
        w.reWriteToWorkingdayTXT("b1:s1:shampooing:0-30:e5,e2", "services.txt");
        w.WriteToWorkingdayTXT("b1:s2:hair cut:0-30:e1,e2", "services.txt");
        w.WriteToWorkingdayTXT("b1:s3:shave:0-30:e3,e5", "services.txt");
        w.WriteToWorkingdayTXT("b1:s4:Women's haircut:0-30:e2", "services.txt");
        w.WriteToWorkingdayTXT("b1:s5:Dye hair service:6-30:e1,e2", "services.txt");
        s.printService("b1","b");
    }

    @After
    public void tearDown() throws Exception {

    }
    //service ID checking
    @Test
    public void checkIDTest(){
        String n = "s1";
    int index = s.checkID(n); //index = 0 indicates an incorrect service number
    assertNotEquals(index,0);

    }
    //write other test cases with different service nnumber formats

    @Test
    public void checkEIDTest() {
        int index = 0;//first line of text file
        String eID = "e5";

        assertTrue(s.checkEID(index,eID)) ;//checks this employee can do this service
    }
    //test employees not in the service and invalid eID values


    @Test
    public void checkEmployeesTest() {
        String newEmployees = "e1,e2,e3"; //added into service
        String b = "b1";
        assertFalse(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    //write cases which are false

    @Test
    public void checkEqualEmployeesTest() {
        String input = "e1,e3,e5,e3";//repeated value
        String b = "b1";
        assertTrue(s.checkEqualEmployees(b,input)); // true if repeated ids
    }

    @Test
    public void checkNameTest() {
        String input = "valid name";
        assertTrue(s.checkName(input));
    }

    @Test
    public void checkDurTest()  {
        String input = "2-00";
        assertTrue(s.checkDur(input));
    }

}