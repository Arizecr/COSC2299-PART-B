package test;
import menu.Login;
import menu.Register;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Yesmi on 10/03/2017.
 */

public class RegisterTesting {
    Register reg = new Register();
    private int incorrect = 1; //
    private int correct = 0;
    private String name = "test";
    private String a = "123 l st";
    private String m = "1234567890";
    @BeforeClass
    public static void loadUsers(){
        Login log = new Login();
        log.loadCustomerInformation();
    }

    @Test
    public void correctUsername() {
        String u = "c123456";
        boolean value = reg.testUser(u);
        assertTrue(value);
    }

    @Test // expect  username to be larger than just c
    public void oneCharCUsername() {
        String u = "c";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test // expect  username to be larger than just c
    public void Namefake() {
        String u = "cnotinsys";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }

    @Test // expect  username to be larger than just c
    public void invalidCharUsername() {

        String u = "a";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test // expect  username to be larger than just c
    public void invalidUsername() {

        String u = "abc123";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test
    public void invalidUsernameUppBound() {

        String u = "abcdef0123456789";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }

    @Test //fixed in code
    public void passwordNull() {
        String u = "c1gsy";
        String p = "";
        int value = reg.testReg(p,name,a,m);
        assert(correct != value);
    }
    @Test
    public void passwordNullUppBound() {
        String u = "c1gsyc1gsy111256";
        String p = "";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);

    }


    @Test
    public void sameUsername() {
        String u = "c12";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test // length 16
    public void invalidUsernameBound() {

        String u = "c123456789101112";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test // length 15
    public void validUsernameBound() {

        String u = "c12345678910112";
        String p = "password";
        int value = reg.testReg(p,name,a,m);
        assertEquals(correct,value);
    }
    @Test // length 14
    public void validUsernameBound2() {

        String u = "c1234978910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertEquals(correct,value);
    }
    @Test // length 16
    public void invalidUsernameBound2() {

        String u = "cccccccccccccccc";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test // length 16
    public void invalidUsernameBoundLarge() {

        String u = "c123459999999999344666666666666";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Test // length 14
    public void validUsernameBound3() {

        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertEquals(correct,value);
    }
    @Test // length 16
    public void invalidUsernameBound3() {

        String u = "bbc1234978910112";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    @Ignore// (expected = NullPointerException.class)//searches and compares to current logins
    public void errorTest2() {

        String u = "";
        String p = "password";
        int value = reg.testReg(p,name,a,m);
    }
}

