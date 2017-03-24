package test;

import user.Customer;
import coreFunctions.WriteToFile;
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
        String name = "Kelly";
        String address = "address";
        String phoneNo = "phone";
        Customer c = new Customer(username, password, name, address, phoneNo);
        WriteToFile w = new WriteToFile();
        w.WriteToTXT(c,"customerinfo.txt");
    }
}