package coreFunctions;

import test.Logging;
import user.Employee;
import userBase.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 5/03/2017.
 */
public class WriteToFile {
    Logging l =new Logging();
    private static final Logger LOGGER = Logger.getLogger( WriteToFile.class.getName() );


    public void WriteToTXT(User person, String txtname){

        try{

            FileWriter fw = new FileWriter(txtname,true); //the true will append the new data

            fw.write("\n");
            fw.write(person.toString());//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            //System.err.println("IOException: " + ioe.getMessage());

             l.Logging();
            LOGGER.log(Level.WARNING,ioe.toString(),ioe);
        }

    }

    public void WriteToEmployee(Employee person, String txtname){

        try{

            FileWriter fw = new FileWriter(txtname,true); //the true will append the new data

            fw.write("\n");
            fw.write(person.toString());//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
           // System.err.println("IOException: " + ioe.getMessage());
             l.Logging();
            LOGGER.log(Level.WARNING,ioe.toString(),ioe);
        }

    }
    //overwrites the current textfile
    public void reWriteToWorkingdayTXT(String chosenwork, String txtname){
        try{
            FileWriter fw = new FileWriter(txtname,false); //the true will append the new data
            fw.write(chosenwork);//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
             l.Logging();
            LOGGER.log(Level.WARNING,ioe.toString(),ioe);
           // System.err.println("IOException: " + ioe.getMessage());
        }

    }
    public void WriteToWorkingdayTXT(String chosenwork, String txtname){

        try{
            FileWriter fw = new FileWriter(txtname,true); //the true will append the new data

            fw.write("\n");
            fw.write(chosenwork);//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
             l.Logging();
            LOGGER.log(Level.WARNING,ioe.toString(),ioe);
            //System.err.println("IOException: " + ioe.getMessage());
        }


    }



}
