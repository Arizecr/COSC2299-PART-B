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
        System.out.println("\nAdd Employee");
        System.out.println("====================");

        //infinite loop
    while(true) {
        String employeeID = generateEmployeeNo();

        /* debug */
        System.out.println("Employee ID is " + employeeID);

        //other stuff

        System.out.print("Enter full name: ");
        String employeeName = reader.nextLine();

        //error check for length of name
        employeeName = verifyEmployeeName(employeeName);



        System.out.print("Enter tax file number: ");

        //checks if numbers are entered for tfn
        while(!reader.hasNextInt()) {
            System.out.println("\nError: entered a non integer as tfn.");
            System.out.print("Enter tax file number: ");
            reader.next();
        }
        int checkTfn = reader.nextInt();
        checkTfn = verifyEmployeeTFN(checkTfn);
        String tfn = Integer.toString(checkTfn);


        System.out.print("Enter phone number: ");

        //checks if numbers are entered for phone no
        while(!reader.hasNextInt()) {
            System.out.println("\nError: a phone number consists of 10 digits.");
            System.out.print("Enter phone number: ");
            reader.next();
        }

        int checkPhone = reader.nextInt();
        checkPhone = verifyEmployeeMobile(checkPhone);
        String phoneNo = Integer.toString(checkPhone);



        filewriter.WriteToEmployee(new Employee(employeeID, employeeName, tfn, phoneNo), "employeeList.txt");
        System.out.println("Successfully added a new employee");
        break;


    }

    }

    /*
     * Checks validity of employee name (length)
     */
    public String verifyEmployeeName(String name){
        Scanner reader = new Scanner(System.in);
        while(name.length()< 3){
            System.out.println("Error: Name must be longer than 2 characters");
            System.out.print("Re-enter full name: ");
            name = reader.nextLine();
        }
        return name;
    }

    /*
     * Checks validity of employee tfn (tfn consists of 8-9 digits)
     */
    public int verifyEmployeeTFN(int tfn){
        Scanner reader = new Scanner(System.in);

        int length = Integer.toString(tfn).length();
        System.out.println(length);

        while(length < 8 || length>9){
            System.out.print("Error: TFN must be 8-9 digits in length.");

            while(!reader.hasNextInt()) {
                System.out.println("\nError: entered a non integer as tfn.");
                System.out.print("Enter tax file number: ");
                reader.next();
            }
            tfn = reader.nextInt();
            length = Integer.toString(tfn).length();

        }

        return tfn;
    }

    /*
     * Checks validity of employee mobile (mobile consists of 10 digits)
     */
    public int verifyEmployeeMobile(int phone){
        Scanner reader = new Scanner(System.in);

        int length = (int) Math.log10(phone) + 1;
        while(length != 10){
            System.out.print("Error: Phone no is 10 digits in length.");

            while(!reader.hasNextInt()) {
                System.out.println("\nError: entered a non integer as tfn.");
                System.out.print("Enter tax file number: ");
                reader.next();
            }
            phone = reader.nextInt();
            length = (int) Math.log10(phone) + 1;

        }

        return phone;
    }



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
