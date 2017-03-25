package coreFunctions;

import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Driver {
    WriteToFile filewriter = new WriteToFile();


    public void addEmployee(){
        Scanner reader = new Scanner(System.in);
        boolean valid = true;
    while(valid) {
        System.out.println("\nAdd Employee");
        System.out.println("====================");

        String employeeID = generateEmployeeNo();

        /* debug */
        System.out.println("Employee ID is " + employeeID);

        //other stuff
        System.out.print("Enter full name: ");
        String employeeName = reader.nextLine();

        System.out.print("Enter tax file number: ");
        String tfn = reader.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNo = reader.nextLine();
        valid = Vemployee(tfn,phoneNo);
        if(valid){
            filewriter.WriteToEmployee(new Employee(employeeID, employeeName, tfn, phoneNo), "employeeList.txt");

            System.out.println("Successfully added a new employee");
        }
    }

    }
    public boolean Vemployee(String tfn, String phoneNo){ return false;}
    /*
     * Generate employee ID
     */
    public String generateEmployeeNo(){
        int count = 1;
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    count++;
                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return "e"+count;
    }


    public void addWorkdays(String dateAndTime, String endTime){
        /*
        Still needs to create a date class for now
        All it does is currently write to txt file to save the work dates.
         */
        String combinedDate = dateAndTime + " " + endTime;

        filewriter.WriteToWorkingdayTXT(combinedDate, "workdaysList.txt");




    }
}
