package coreFunctions;

import bookings.Bookings;
import bookings.CurrentBookings;
import bookings.PastBookings;
import user.Employee;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Driver {
    WriteToFile filewriter = new WriteToFile();
    public static ArrayList<String> hours = new ArrayList<>();
    public static ArrayList<Bookings> currentBookings = new ArrayList<>();
    public static ArrayList<Bookings> pastBookings = new ArrayList<>();

    public void loadCurrentBookings() {
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("currentBookings.txt"));

            try {
                String x;
                while ((x = br.readLine()) != null) {
                    // printing out each line in the file
                    String Details[] = x.split(",", 4);
                    String day = Details[0];
                    String customer = Details[1];
                    String time = Details[2];
                    String service = Details[3];
                    CurrentBookings bookingInfo = new CurrentBookings(day, customer, time, service);
                    currentBookings.add(bookingInfo);
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

    }

    public void loadPastBookings() {
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("pastBookings.txt"));

            try {
                String x;
                while ((x = br.readLine()) != null) {
                    // printing out each line in the file
                    String Details[] = x.split(",", 5);
                    String day = Details[0];
                    String customer = Details[1];
                    String time = Details[2];
                    String service = Details[3];
                    String cancelled = Details[4];
                    PastBookings bookingInfo = new PastBookings(day, customer, time, service, cancelled);
                    pastBookings.add(bookingInfo);
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

    }



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

    //adds the dates into workdaysList.txt
    public void addWorkdays(String bId, String empId, String day, String startTime, String endTime){

        String combinedData = bId+" "+empId + " " +day + " "+startTime + " "+ endTime;
        File file = new File("workdaysList.txt");
        if(file.length()==0)// if file is empty data added to firt line
        {
            filewriter.reWriteToWorkingdayTXT(combinedData, "workdaysList.txt");}
        else{// if not empty adds data to the nextline
            filewriter.WriteToWorkingdayTXT(combinedData, "workdaysList.txt");}
    }


    public boolean timeCheck(String s,String end,String Bs,String Be){
        DateFormat time = new SimpleDateFormat("EEEE");
        try {

            Date st = time.parse(s);
            Date et = time.parse(end);
            Date Bst = time.parse(Bs);
            Date Bet = time.parse(Be);


            // checks if employee worktimes are now outside new constraints
            if (et.equals(Bet)&&st.equals(Bst)){return false;}
            if (et.before(Bet)&&st.equals(Bst)){return false;}
            if (et.equals(Bet)&&st.after(Bst)){return false;}
            if (et.after(Bet)||st.after(Bet)) { return true;}
            else if (st.before(Bst)||et.before(Bst)) {return true;}

        } catch (ParseException e) {
            return true;
        }
        return false;
    }

    //loads employee work days
    public void loadInfo(){
        hours = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("workdaysList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    hours.add(x);
                }
                //prints error
            } catch (IOException error) {
                error.printStackTrace();
            }

            //file cannot be found
        } catch (FileNotFoundException error) {
            System.out.println(error);
            error.printStackTrace();
        }

    }
    //new employee worktimes written to file
    public void loadandWriteNEmployeeWorktimes(String b,String d,String s,String e){
        loadInfo();
        int count = 1;
        for(int i=0; i < hours.size() ;i++) {

            // printing out each line in the file
            String Details[] = hours.get(i).split(" ",5);
            String bId = Details[0];
            String empID = Details[1];
            String day = Details[2];
            String start = Details[3];
            String end = Details[4];

            if((b.equals(bId)&&d.equals(day))&&!timeCheck(start,end,s,e)) {
                if(count ==1){filewriter.reWriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                    count++;
                }
            }
            else if(!(b.equals(bId)&&d.equals(day))) {

                if (count == 1) {
                    filewriter.reWriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                    count++;
                } else
                {
                    filewriter.WriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                }
            }
            else{filewriter.reWriteToWorkingdayTXT("", "workdaysList.txt");}
        }


    }
    //deletes selected work hours for specific day from employee
    public void deleteEmployeeWorktimes(String b,String e,String d){
        loadInfo();
        int count = 1;
        for(int i=0; i < hours.size() ;i++) {

            // printing out each line in the file
            String Details[] = hours.get(i).split(" ", 5);
            String bId = Details[0];
            String empID = Details[1];
            String day = Details[2];


            if (!(e.equals(empID) && b.equals(bId) && d.equals(day))) {
                if (count == 1) {
                    filewriter.reWriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                    count++;
                } else {
                    filewriter.WriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                }
            }
            else{
                if (count == 1) {
                    filewriter.reWriteToWorkingdayTXT("", "workdaysList.txt");
                }
                System.out.println("Shifts of employee " + empID + " for " + day + " have been removed");
            }
        }
    }

    /*
     * View current bookings including past bookings
     */
    public void viewBookings(){
        Scanner reader = new Scanner(System.in);
        loadCurrentBookings();
        loadPastBookings();

        System.out.println("\n+----------------------------------+");
        System.out.println("|               View               |");
        System.out.println("|             Bookings             |");
        System.out.println("+----------------------------------+");

        System.out.println("\n1. View Current Bookings");
        System.out.println("2. View Past Bookings");
        System.out.print("Choose option (1-2): ");

        while(!reader.hasNextInt()) {
            System.out.println("Error: entered a non integer. Enter a number between 1-3.");
            System.out.print("Enter choice (1-3): ");
            reader.next();
        }

        int choice = reader.nextInt();

        //infinite loop
        while(true){
            if(choice == 1){ //view current bookings
                for(int i=0; i<currentBookings.size();i++){
                    System.out.println(currentBookings.get(i).getDayBooked());
                    System.out.println(currentBookings.get(i).getTimeBooked());
                    System.out.println(currentBookings.get(i).getCustomer());
                    System.out.println(currentBookings.get(i).getServiceBooked());

                }
                break;

            }

            else if(choice ==2){ //view past bookings
                for(int i=0; i<pastBookings.size();i++){
                    System.out.println(pastBookings.get(i).getDayBooked());
                    System.out.println(pastBookings.get(i).getTimeBooked());
                    System.out.println(pastBookings.get(i).getCustomer());
                    System.out.println(pastBookings.get(i).getServiceBooked());
                    //also add cancelled status

                }
                break;
            }

            else {
                System.out.println("Error."); //fix this later
                break;

            }
        }
    }

    //remove all booking on this day
    public void removeWorktimes(String b,String d) {
        loadInfo();
        int count = 1;
        for (int i = 0; i < hours.size(); i++) {

            // printing out each line in the file
            String Details[] = hours.get(i).split(" ", 5);
            String bId = Details[0];
            String day = Details[2].toLowerCase();

            if (!(b.equals(bId)&&d.equals(day))) {

                if (count == 1) {
                    filewriter.reWriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                    count++;
                } else {
                    filewriter.WriteToWorkingdayTXT(hours.get(i), "workdaysList.txt");
                }
            }
            else {
                if (count<= 1) {
                    filewriter.reWriteToWorkingdayTXT("", "workdaysList.txt");
                }
            }
        }
    }
    //check if there is a shift already during this time
    public boolean checkWorktimes(String b, String emp, String d, String s , String e) {
        loadInfo();
        int count = 1;
        for (int i = 0; i < hours.size(); i++) {

            // printing out each line in the file
            String Details[] = hours.get(i).split(" ", 5);
            String bId = Details[0];
            String empID = Details[1];
            String day = Details[2].toLowerCase();
            String start = Details[3];
            String end = Details[4];
            if (b.equals(bId) && d.equals(day) && emp.equals(empID)) {
                DateFormat time = new SimpleDateFormat("HH:mm");
                count++;
                try {
                    Date Nst = time.parse(s);
                    Date Net = time.parse(e);
                    Date Cst = time.parse(start);
                    Date Cet = time.parse(end);

                    // This makes sure scheduled employee shift is within operating hours of business
                    if (((Nst.after(Cst)&&Nst.before(Cet))||Nst.equals(Cst))) {
                        System.out.println("employee has shift during this time");
                        return true;

                    } else if ((Net.before(Cet)&&Net.after(Cst))||Net.equals(Cet)) {
                        System.out.println("employee has shift during this time");
                        return true;
                    }
                } catch (ParseException ex) {
                    System.out.println("Invalid Time");
                    return true;
                }

            }
        }
        return false;
    }

    public void printEmployeeWorktimes(String bId, String employeeID){
        Employee emp = new Employee();
        Scanner eID = new Scanner(System.in);
        String empID = employeeID;
        String name = emp.getEmployeeName(bId,empID);
        loadInfo();
        System.out.println("-----------------------------------------");
        System.out.println("The current times "+name+" is working:");
        for(int i=0; i < hours.size() ;i++) {

            // printing out each line in the file
            String Details[] = hours.get(i).split(" ",5);


            String e = Details[0];
            String day = Details[2].toLowerCase();
            String start = Details[3];
            String end = Details[4];

            //change day to uppercase
            day = day.substring(0,1).toUpperCase() + day.substring(1).toLowerCase();


            if(bId.equals(e)){
                System.out.println(day + " " +start+ " - " + end);
            }


        }
        System.out.println("-----------------------------------------");

    }



    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}