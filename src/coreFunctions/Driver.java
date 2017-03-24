package coreFunctions;

import user.Employee;

import java.util.Date;
import java.util.Scanner;
/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Driver {
    WriteToFile filewriter = new WriteToFile();


    public void addEmployee(){
        Scanner reader = new Scanner(System.in);

        System.out.println("\nAdd Employee");
        System.out.println("====================");

        //to implement: automatically generate employee id
        String employeeID = "e2";

        //other stuff
        System.out.println("Enter full name: ");
        String employeeName = reader.nextLine();

        System.out.println("Enter tax file number");
        String tfn = reader.nextLine();
        System.out.println("Enter phone number");
        String phoneNo = reader.nextLine();

        filewriter.WriteToEmployee(new Employee(employeeID, employeeName, tfn, phoneNo), "employeeList.txt");

        System.out.println("Successfully added a new employee");
    }


    public void addWorkdays(Date dateAndTime){
        /*
        Still needs to create a date class for now
        All it does is currently write to txt file to save the work dates.
         */
        filewriter.WriteToWorkingdayTXT(dateAndTime, "workdaysList.txt");




    }
}
