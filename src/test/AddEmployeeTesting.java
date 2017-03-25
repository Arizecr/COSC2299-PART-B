package test;
import coreFunctions.Driver;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Created by yesmi on 25/03/2017.
 */
public class AddEmployeeTesting {

    Driver driver = new Driver();
    Boolean details;
    String tfn = "123456789";
    String mobile = "0412345678";
    String name = "Employee";

    @BeforeClass
    public static void loadDriver(){

    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewEmployee9() {
        details = driver.verifyEmployeeTFN(tfn);
        assertFalse(details);
    }
    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewEmployee8() {
        tfn = "12345678";
        details = driver.verifyEmployeeTFN(tfn);
        assertFalse(details);
    }

    @Test
    public void correctUppBoundEmployeeMobile() {
        details = driver.verifyEmployeeMobile(mobile);
        assertFalse(details);
    }

    @Test//not a number
    public void fakeNewEmployeeTFN() {
        details = driver.verifyEmployeeTFN("123a67890");
        assertTrue(details);
    }
    @Test //not a number
    public void fakeNewEmployeeNO() {
        details = driver.verifyEmployeeMobile("04sascgf");
        assertTrue(details);
    }
    @Test
    public void fakeNewEmployeeNO2() {
        details = driver.verifyEmployeeMobile("qwe");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN() {
        details = driver.verifyEmployeeTFN("12345678908");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO() {
        details = driver.verifyEmployeeMobile("12345678900");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN2() {
        details = driver.verifyEmployeeTFN("123456789082");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO2() {
        details = driver.verifyEmployeeMobile("123456789002");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN3() {
        details = driver.verifyEmployeeTFN("1234567890823");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO3() {
        details = driver.verifyEmployeeMobile("1234567890023");
        assertTrue(details);
    }
    @Test
    public void NullNewEmployeeTFN() {
        details = driver.verifyEmployeeTFN("");
        assertTrue(details);
    }
    @Test
    public void NullNewEmployeeNO() {
        details = driver.verifyEmployeeMobile("");
        assertTrue(details);
    }


}