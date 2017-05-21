package test;

import menu.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yesmi on 10/03/2017.
 */
public class LoginTesting {
    Login login = new Login();
    boolean toVerify = true;
    @BeforeClass
    public static void loadUsers(){
        Login log = new Login();
        log.loadCustomerInformation();
        log.getOwnerinfo();
    }
    //---------------------------------------------------------- customer testing
    @Test
    public void verifyLoginAll() {
        toVerify =  login.getVerification("customer","c1","123");
        assertTrue(toVerify);
    }

    @Test //invalid Type + correct user Details
    public void IncorrectUN() {
        toVerify =  login.getVerification("c","c1","123");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectUNBoundary() {
        toVerify =  login.getVerification("c","c0000000000000000000000000000000001","123");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectUNBoundary2() {
        toVerify =  login.getVerification("c","c0000000000abc000000000000000000000001","123");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectPW() {
        toVerify =  login.getVerification("customer","c1","c");
        assertFalse(toVerify);
    }

    //---------------------------------------------------business owner testing
    @Test //invalid Type + user Details
    public void verifyLoginAllB() {
        toVerify =  login.getVerification("business","b1","123");
        assertTrue(toVerify);
    }
    @Test
    public void IncorrectUNB() {
        toVerify =  login.getVerification("business","b1notrealusername","123");
        assertFalse(toVerify);
    }

    @Test //invalid Type + correct user Details
    public void IncorrectUNB2() {
        toVerify =  login.getVerification("business","b1notrealus00000000000000000000ername","123");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectUNBBoundary() {
        toVerify =  login.getVerification("business","b0000000000000000000000000000000001","123");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectUNBBoundary2() {
        toVerify =  login.getVerification("business","b0000000000abc000000000000000000000001","123");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectPWB() {
        toVerify =  login.getVerification("business","b1","fakepassword");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectPWB2() {
        toVerify =  login.getVerification("business","b1","ccccccccccccccccccccccccccccccccccccccc");
        assertFalse(toVerify);
    }



}