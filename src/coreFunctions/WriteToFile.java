package coreFunctions;

import user.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Martin on 5/03/2017.
 */
public class WriteToFile {


    public void WriteToTXT(Customer person, String txtname){

        try{

            FileWriter fw = new FileWriter(txtname,true); //the true will append the new data

            fw.write("\n");
            fw.write(person.toString());//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void WriteToWorkingdayTXT(Date datentime, String txtname){


        try{

            FileWriter fw = new FileWriter(txtname,true); //the true will append the new data

            fw.write("\n");
            fw.write(datentime.toString());//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }


    }







}
