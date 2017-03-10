package Test;

import Menu.Login;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yesmi on 10/03/2017.
 */
public class LoginTesting {

    @Test //invalid Type + User Details
    public void verifyLoginAll() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("c","cc","c");
        assertFalse(toVerify);
    }

    @Test //invalid Type + User Details
    public void IncorrectUN() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("c","cc","c");
        assertFalse(toVerify);
    }

    @Test //invalid Type + User Details
    public void IncorrectPW() {
        Login login = new Login();
        boolean toVerify =  login.getVerification("c","cc","c");
        assertFalse(toVerify);
    }

}