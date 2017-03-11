package Test;

import Menu.Register;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by yesmi on 10/03/2017.
 */

public class RegisterTesting {
    Register reg = new Register();
    int incorrect = 1; //
    int correct = 0;
    @Test
    public void correctUsername() {
        String u = "c1";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(value,correct);
    }

    @Test // expect  username to be larger than just c
    public void oneCharCUsername() {
        String u = "c";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(value,incorrect);
    }
    @Test // expect  username to be larger than just c
    public void invalidCharUsername() {

        String u = "a";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(value,incorrect);
    }
    @Test // expect  username to be larger than just c
    public void invalidUsername() {

        String u = "abc123";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(value,incorrect);
    }
    @Test //(expected = ArrayIndexOutOfBoundsException.class)
    public void passwordNull() {//needs to be addressed when fixing code

        String u = "c1";
        String p = null;
        int value = reg.testReg(u,p);
        assertEquals(value,incorrect);
    }
    @Test //(expected = ArrayIndexOutOfBoundsException.class)
    public void registerMenuTest() {
        String u = "c12";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(value,0);
    }
}