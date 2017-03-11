package Test;

import Menu.Register;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yesmi on 10/03/2017.
 */
public class RegisterTesting {

    @Test
    public void correctUsername() {
        Register reg = new Register();
        String u = "c1";
        String p = "password";
        int value = reg.testReg(u,p);
        int expected = 0;
        assertEquals(value,expected);
    }

    @Test //(expected = ArrayIndexOutOfBoundsException.class)
    public void oneCharUsername() {
        Register reg = new Register();
        String u = "c";
        String p = "password";
        int value = reg.testReg(u,p);
        int expected = 1;
        assertEquals(value,expected);
    }

    @Test //(expected = ArrayIndexOutOfBoundsException.class)
    public void registerMenuTest() {
        Register reg = new Register();
        String u = "c1";
        String p = "password";
        int value = reg.testReg(u,p);
        int expected = 0;
        assertEquals(value,expected);
    }
}