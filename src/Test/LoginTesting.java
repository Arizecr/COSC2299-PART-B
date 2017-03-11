package Test;

import Menu.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yesmi on 10/03/2017.
 */
public class LoginTesting {
    @BeforeClass
    public static void loadUsers(){
        Login log = new Login();
        log.loadCustomerInformation();
        log.getOwnerinfo();
    }
////////////////////////////////////user testing
    @Test
    public void verifyLoginAll() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("customer","c1","123");
        assertTrue(toVerify);
    }

    @Test //invalid Type + correct User Details
    public void IncorrectUN() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("c","c1","123");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectPW() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("customer","c1","c");
        assertFalse(toVerify);
    }
    ////////////////////////////////////business owner testing
    @Test //invalid Type + User Details
    public void verifyLoginAllB() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("business","b1","123");
        assertTrue(toVerify);
    }

    @Test
    public void IncorrectUNB() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("business","b1notrealusername","123");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectPWB() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("business","b1","fakepassword");
        assertFalse(toVerify);
    }
}