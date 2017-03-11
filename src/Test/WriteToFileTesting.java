package Test;

import Actor.Customer;
import CoreFunctions.WriteToFile;
import org.junit.Test;

/**
 * Created by yesmi on 10/03/2017.
 */

public class WriteToFileTesting {
    @Test
    public void write()
    {
        String username = "c";
        String password = "12345";
        Customer c = new Customer(username, password);
        WriteToFile w = new WriteToFile();
        w.WriteToTXT(c,"customerinfo.txt");
    }
}