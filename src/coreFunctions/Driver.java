package coreFunctions;

import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Driver {
    WriteToFile filewriter = new WriteToFile();


    public void addEmployee(String bId){
        Scanner reader = new Scanner(System.in);

        boolean valid = true;
        System.out.println("\nAdd Employee");
        System.out.println("====================");

        //infinite loop
        while(true) {
            String employeeID = generateEmployeeNo(); //generate user id

            System.out.println("Employee ID is " + employeeID);

            String employeeName;
            do {
                System.out.print("Enter full name: ");
                employeeName = reader.nextLine();
            }while(verifyEmployeeName(employeeName)); //error check for length of name IN WHILE CONDITION


            String checkTfn;
            do{
                System.out.print("Enter tax file number (8-9 digits): ");
                checkTfn = reader.nextLine();
            }while(verifyEmployeeTFN(checkTfn)); //checks if numbers are entered for tfn
            String tfn = checkTfn;

            String checkPhone;
            do {
                System.out.print("Enter phone number (10 digits): ");
                checkPhone = reader.nextLine();
            }while(verifyEmployeeMobile(checkPhone));//checks if numbers are entered for phone no

            String phoneNo = checkPhone;



            filewriter.WriteToEmployee(new Employee(bId,employeeID, employeeName, tfn, phoneNo), "employeeList.txt");
            System.out.println("Successfully added a new employee");
            break;


        }

    }

    /*
     * Checks validity of employee name (length)
     */

    public Boolean verifyEmployeeName(String name){
        if((name.length()< 3)||(name.length()>20)){
            System.out.println("Error: Name must be longer than 2 characters");
            //System.out.print("Re-enter full name: ");
            return true;
        }
        return false;
    }

    /*
     * Checks validity of employee tfn (tfn consists of 8-9 digits)
     */
    public Boolean verifyEmployeeTFN(String tfn){
        int length = tfn.length();

        if(length < 8 || length>9){
            System.out.println("Error: TFN must be 8-9 digits in length.");
           return true;
        }
        if(!isNumeric(tfn)) {
            System.out.println("Error: entered a non integer as tfn.");
           // System.out.print("Enter tax file number: ");
            return true;
        }
        return false;
    }

    /*
     * Checks validity of employee mobile (mobile consists of 10 digits)
     */
    public Boolean verifyEmployeeMobile(String phone){
        if(phone.length() != 10){
            System.out.println("Error: Phone no is 10 digits in length.");
            return true;
        }
        if(!isNumeric(phone)) {
            System.out.println("Error: entered a non integer as tfn.");
            return true;
        }
        return false;
    }



    /*
     * Generate employee ID
     */
    private String generateEmployeeNo(){
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


    public void addWorkdays(String bId, String empId, String day, String startTime, String endTime){
        /*
        Still needs to create a date class for now
        All it does is currently write to txt file to save the work dates.
         */
        String combinedData = bId+" "+empId + " " +day + " "+startTime + " "+ endTime;

        filewriter.WriteToWorkingdayTXT(combinedData, "workdaysList.txt");




    }


    public boolean timeCheck(String s,String end,String Bs,String Be){
        DateFormat time = new SimpleDateFormat("EEEE");
        try {

            Date st = time.parse(s);
            Date et = time.parse(end);
            Date Bst = time.parse(Bs);
            Date Bet = time.parse(Be);


            // checks if employee worktimes are now outside new constraints
            if (et.after(Bet)||st.after(Bet)) {

                return true;

            } else if (st.before(Bst)||et.before(Bst)) {

                return true;
            }

        } catch (ParseException e) {

            return true;

        }
        return false;
    }
    public void loadandWriteNEmployeeWorktimes(String b,String d,String s,String e){
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("workdaysList.txt"));

            try {
                String x;
                int count = 1;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",5);
                    String bId = Details[0];
                    String empID = Details[1];
                    String day = Details[2];
                    String start = Details[3];
                    String end = Details[4];
                    String combinedData = bId+" "+empID + " " +day + " "+start + " "+ end;
                    if(b.equals(bId)&&d.equals(day)&&timeCheck(start,end,s,e)) {
                       if(count ==1){filewriter.reWriteToWorkingdayTXT(null, "workdaysList.txt");
                           count++;}
                    }
                    else{
                        if (count == 1) {
                            filewriter.reWriteToWorkingdayTXT(combinedData, "workdaysList.txt");
                            count++;
                        } else
                            {
                            filewriter.WriteToWorkingdayTXT(combinedData, "workdaysList.txt");
                        }
                    }
                }
                //prints error
            } catch (IOException error) {
                error.printStackTrace();
            }


            //file cannot be found
        } catch (FileNotFoundException error) {
            System.out.println(e);
            error.printStackTrace();
        }

    }




    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}