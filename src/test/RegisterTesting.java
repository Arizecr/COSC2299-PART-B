package test;
import menu.Login;
import menu.Register;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Yesmi on 10/03/2017.
 */

public class RegisterTesting {
    Register reg = new Register();
    private int incorrect = 1; //
    private int correct = 0;
    private String name = "testing";
    private String a = "123 lm st";
    private String m = "0434567890";

    @BeforeClass
    public static void loadUsers(){
        Login log = new Login();
        log.loadCustomerInformation();

    }

    @Test
    public void correctUsername() {
        String u = "c12345006";
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
    public void sameUsername() {
        String u = "c12";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }
    //------------------------------------------------------Boundary testing Username
    @Test
    public void invalidUsernameUppBound() {

        String u = "abcdef0123456789";
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

        String u = "c1234567812";
        String p = "password";
        boolean value = reg.testUser(u);
        assertTrue(value);
    }
    @Test // length 14
    public void validUsernameBound2() {

        String u = "c1234978910112";
        boolean value = reg.testUser(u);
        assertTrue(value);
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
        boolean value = reg.testUser(u);
        assertTrue(value);
    }
    @Test // length 16
    public void invalidUsernameBound3() {

        String u = "bbc1234978910112";
        boolean value = reg.testUser(u);
        assertFalse(value);
    }//-------------------------------------------------------------password testing

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
    public void allNull() {
        String u = "";
        String p = "";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);

    }
    //------------------------------------------------------------Mobile
    @Test
    public void validMobile() {

        String p = "passord";
        int value = reg.testReg(p,"mary","abc 123","0456789098");
        assertEquals(correct,value);
    }
    @Test
    public void invalidMobile() {
        String m = "qwertyuiop";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidMobile2() {
        String m = "0000000000";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidMobileformat() {
        String m = "0312345678";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidMobileformat2() {
        String m = "1234567890";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void nullMobile() {
        String m = "";
        String u = "cccccc10112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }


    //--------------------------------------------------------------Boundary testing Mobile
    @Test
    public void invalidMobileSize() {
        String m = "04123456789";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidMobileSize2() {
        String m = "041234567890";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidMobileSize3() {
        String m = "0412345678889";
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,a,m);
        assertNotEquals(correct,value);
    }

    //------------------------------------------------------name
    @Test
    public void invalidName() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"",a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void validName() {
        String u = "c10112";
        String p = "passwooord";
        int value = reg.testReg(p,"Anne  marie",a,m);
        assertEquals(correct,value);
    }
    @Test
    public void validName2() {
        String u = "c10112";
        String p = "passwooord";
        int value = reg.testReg(p,"Aimee",a,m);
        assertEquals(correct,value);
    }
    @Test
    public void invalidName2() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"332",a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidName3() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"Claire8",a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidName4() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"33ijpow 2",a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidName5() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"jpow'",a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidName6() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"jpow-",a,m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidName7() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,"jpow^_",a,m);
        assertNotEquals(correct,value);
    }
    //---------------------------------------address can contain numbers and letters
    @Test
    public void invalidAddress() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,"",m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidAddress2() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,"dfgh 78uji ';",m);
        assertNotEquals(correct,value);
    }
    @Test
    public void invalidAddress3() {
        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(p,name,"dfgh 78uji _",m);
        assertNotEquals(correct,value);
    }


}

