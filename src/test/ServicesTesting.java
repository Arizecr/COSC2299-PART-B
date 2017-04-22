package test;

import bookings.Services;
import coreFunctions.WriteToFile;
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
        w.reWriteToWorkingdayTXT("b1:s1:shampooing:0-30:12:e5,e2", "services.txt");
        w.WriteToWorkingdayTXT("b1:s2:hair cut:0-30:40:e1,e2", "services.txt");
        w.WriteToWorkingdayTXT("b1:s3:shave:0-30:10:e3,e5", "services.txt");
        w.WriteToWorkingdayTXT("b1:s4:Women's haircut:0-30:50:e2", "services.txt");
        w.WriteToWorkingdayTXT("b1:s5:Dye hair service:6-30:200:e1,e2", "services.txt");
        s.printService("b1","b");
    }

    //------------------------------------------------------------------service ID checking
    @Test
    public void checkIDTest(){
        String n = "s1";
    int index = s.checkID(n); //index = 0 indicates an incorrect service number
    assertNotEquals(index,0);

    }
    @Test
    public void checkIDFake(){
        String n = "s001";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }
    @Test
    public void checkIDFake2(){
        String n = "001";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }
    @Test
    public void checkIDFake3(){
        String n = "asdfghjkl001";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }
    @Test
    public void checkIDNull(){
        String n = "";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }

    //-------------------------------------------------------------------employee ID checking----
    //---------------------------------if employee does this service
    @Test
    public void checkEIDTestS1() {
        int index = 0;//first line of text file
        String eID = "e5";

        assertTrue(s.checkEID(index,eID)) ;//checks this employee can do this service
    }
    @Test
    public void checkEIDTestS2() {
        int index = 1;//first line of text file
        String eID = "e1";

        assertTrue(s.checkEID(index,eID)) ;//checks this employee can do this service
    }
    @Test
    public void checkEIDTestS3() {
        int index = 2;//first line of text file
        String eID = "e3";

        assertTrue(s.checkEID(index,eID)) ;//checks this employee can do this service
    }
    @Test
    public void checkEIDTestS4() {
        int index = 3;//first line of text file
        String eID = "e2";
        assertTrue(s.checkEID(index,eID)) ;//checks this employee can do this service
    }
    @Test
    public void checkEIDTestFakeS2() {
        int index = 1;//first line of text file
        String eID = "e5";

        assertFalse(s.checkEID(index,eID)) ;//checks this employee does not do this service
    }
    @Test
    public void checkEIDTestFakeS3() {
        int index = 2;//first line of text file
        String eID = "e1";

        assertFalse(s.checkEID(index,eID)) ;//checks this employee does not do this service
    }
    @Test
    public void checkEIDTestFakeS4() {
        int index = 3;//first line of text file
        String eID = "e1";
        assertFalse(s.checkEID(index,eID)) ;//checks this employee does not do this service
    }
    @Test
    public void checkEIDTestNull() {
        int index = 3;//first line of text file
        String eID = "";
        assertFalse(s.checkEID(index,eID)) ;//checks this employee does not do this service
    }
   //-----------------------------------------invalid eID values
    @Test
    public void checkEmployeesTest() {
        String newEmployees = "e1,e2,e3"; //added into service
        String b = "b1";
        assertFalse(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployee() {
        String newEmployees = "e1"; //added into service
        String b = "b1";
        assertFalse(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesTest2() {
        String newEmployees = "e,e2,e3"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }

    @Test
    public void checkEmployeesIncorrectInput() {
        String newEmployees = "ee2,e3"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput2() {
        String newEmployees = "s4,e5,r,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput3() {
        String newEmployees = " ,s4,e5,r,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput4() {
        String newEmployees = "s4,e 5,r,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput5() {
        String newEmployees = "s4,e,5,r,,,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput6() {
        String newEmployees = "e,,,,,,,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }

    @Test
    public void checkEmployeesIncorrectInput7() {
        String newEmployees = " "; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput8() {
        String newEmployees = ",,,,,,,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput9() {
        String newEmployees = ",#$%$*^(*((),,"; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    @Test
    public void checkEmployeesIncorrectInput10() {
        String newEmployees = ""; //added into service
        String b = "b1";
        assertTrue(s.checkEmployees(b,newEmployees));//if all employees wrk for the business return false;
    }
    //ADD MORE WITH INVALID DATA FORMATS e.g. s4,e5,r, OR WITH SPACES AND EXTRA COMMAS ETC.
    //----------------------------------------repeated Employee
    @Test
    public void checkEqualEmployeesTest() {
        String input = "e1,e3,e5,e3";//repeated value
        String b = "b1";
        assertTrue(s.checkEqualEmployees(b,input)); // true if repeated ids
    }
    @Test
    public void checkEqualEmployeesTest2() {
        String input = "e1,e30,e5,e30";//repeated value
        String b = "b1";
        assertTrue(s.checkEqualEmployees(b,input)); // true if repeated ids
    }
    @Test
    public void checkMultipleEqualEmployees() {
        String input = "e1,e30,e5,e30,e5";//repeated value
        String b = "b1";
        assertTrue(s.checkEqualEmployees(b,input)); // true if repeated ids
    }
    @Test
    public void checkMultipleEqualEmployees2() {
        String input = "e1,e30,e5,e30,e5,e1,e1";//repeated value
        String b = "b1";
        assertTrue(s.checkEqualEmployees(b,input)); // true if repeated ids
    }


    //--------------------------------------------------------------------------service details checking-----------
    //--------------------------------------name checks
    @Test
    public void checkNameTest() {
        String input = "valid name";
        assertTrue(s.checkName(input));
    }
    @Test
    public void checkNameMINBoundary() {
        String input = "1234";//minimum length of name
        assertTrue(s.checkName(input));
    }//MORE OF THIS TYPE
    @Test
    public void checkNameMAXBoundary() {
        String input = "1234567890QWERTY";//max length of name
        assertTrue(s.checkName(input));
    }
    @Test
    public void checkNameAboveMAXBoundary() {
        String input = "123456789099WERTY";//above max length of name
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkNameAboveMAXBoundary2() {
        String input = "123456789099WER0TY";//above max length of name
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkNameAboveMAXBoundary3() {
        String input = "123456789099WERT3Y";//above max length of name
        assertFalse(s.checkName(input));
    }
    //---------------------------------------time taken format check
    @Test
    public void checkDurTest()  {
        String input = "2-00";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTest2()  {
        String input = "2-0";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMin()  {
        String input = "0-30";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMinBound()  {
        String input = "0-10";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMinBound2()  {
        String input = "0-1";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMinBound0()  {
        String input = "0-0";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestAlmostMax()  {
        String input = "7-30";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMax()  {
        String input = "8-00";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMax2()  {
        String input = "8-0";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMax3()  {
        String input = "08-0";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBound()  {
        String input = "08-01";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBound2()  {
        String input = "8-30";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBound3()  {
        String input = "8-3";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBoundLARGE()  {
        String input = "998-30";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestNotInt()  {
        String input = "0a-a1";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestNotIntWrongForm()  {
        String input = "0aa1";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestNotIntWrongForm2()  {
        String input = "1";
        assertFalse(s.checkDur(input));
    }

}