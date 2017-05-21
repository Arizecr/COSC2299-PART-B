package test;

import menu.BusinessMenu;
import org.junit.Test;
import user.Employee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by yesmi on Monday.
 */
public class checkIDTest {
    Employee emp = new Employee();

    BusinessMenu b = new BusinessMenu();
    boolean toVerify;
    String bId = "b1";
    String empID ="e1";
    @Test
    public void verifyLoginAll() {
        toVerify = emp.checkEmployeeID(bId,empID);
        assertTrue(toVerify);
    }

    @Test //invalid Type + correct user Details
    public void IncorrectBID() {
        toVerify = emp.checkEmployeeID("abc",empID);
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectEID() {
        toVerify = emp.checkEmployeeID(bId,"e333");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectALL() {
        toVerify = emp.checkEmployeeID("fake","e333");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectLARGE1() {
        toVerify = emp.checkEmployeeID("fakllllllllllllllllllllllllllllle","e333");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectLARGE2() {
        toVerify = emp.checkEmployeeID("fakeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeeeee3");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectLARGE3() {
        toVerify = emp.checkEmployeeID("fakllllllllllllllllllllllllllllle","e3323w4erdtfgbvhgftr6yguhjkhgy3");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectNULL() {
        toVerify = emp.checkEmployeeID("","e333");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectNULL2() {
        toVerify = emp.checkEmployeeID("","");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectNULL3() {
        toVerify = emp.checkEmployeeID("b1","");
        assertFalse(toVerify);
    }



}