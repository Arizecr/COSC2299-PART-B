package coreFunctions;

import bookings.Bookings;
import bookings.CurrentBookings;
import bookings.PastBookings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import test.Logging;
import user.Employee;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Driver {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    WriteToFile filewriter = new WriteToFile();
    public static ArrayList<String> hours = new ArrayList<>();
    public static ArrayList<Bookings> currentBookings = new ArrayList<>();
    public static ArrayList<Bookings> pastBookings = new ArrayList<>();
    Logging l =new Logging();

    /*
     * loads current booking information
     */
    public void loadCurrentBookings(String b) {
        currentBookings = new ArrayList<>();
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("currentBookings.txt"));

            try {
                String x;
                while ((x = br.readLine()) != null) {
                    // printing out each line in the file
                    String Details[] = x.split(",", 7);
                    String business = Details[0];
                    String day = Details[1];
                    String date = Details[2];
                    String customer = Details[3];
                    String time = Details[4];
                    String service = Details[5];
                    String id = Details[6];

                    CurrentBookings bookingInfo = new CurrentBookings(business,day,date, customer, time, service, id);
                    if(b.equals(business))  currentBookings.add(bookingInfo);
                }
                //prints error
            } catch (IOException e) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }



            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.SEVERE,e.toString(),e);
        }

    }

    /*
     * loads information for past bookings
     */
    public void loadPastBookings(String b) {
        pastBookings = new ArrayList<>();
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("pastBookings.txt"));

            try {
                String x;
                while ((x = br.readLine()) != null) {
                    // printing out each line in the file
                    String Details[] = x.split(",", 8);
                    String business = Details[0];
                    String day = Details[1];
                    String date = Details[2];
                    String customer = Details[3];
                    String time = Details[4];
                    String service = Details[5];
                    String cancelled = Details[6];
                    String id = Details[7];
                    PastBookings bookingInfo = new PastBookings(business,day,date, customer, time, service, cancelled,id);
                    if(b.equals(business)) pastBookings.add(bookingInfo);
                }
                //prints error
            } catch (IOException e) {
                //e.printStackTrace();\
                l.Logging();
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

    }



    /*
     * Checks validity of employee name (length)
     */

    public Boolean verifyEmployeeName(String name){
        if((name.length()< 3)||(name.length()>20)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Name entered must be longer than 2 characters. Try again.");

            alert.showAndWait();

            return true;
        }
        if(!name.matches("^[a-zA-Z\\s]+$")){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("A valid name does not contain any numbers. Try again.");

            alert.showAndWait();
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
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: TFN must be 8-9 digits in length.");

            alert.showAndWait();

            return true;
        }
        if(!isNumeric(tfn)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: entered a non integer as tfn.");

            alert.showAndWait();

            return true;
        }
        return false;
    }

    /*
     * Checks validity of employee mobile (mobile consists of 10 digits)
     */
    public Boolean verifyEmployeeMobile(String phone){
        if(phone.length() != 10){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Phone number must be 10 digits in length. Try again.");

            alert.showAndWait();
            return true;
        }

        if(!isNumeric(phone)) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("A phone number consists of 10 digits. You have entered non-numeric characters.");

            alert.showAndWait();
            return true;
        }
        if(phone.charAt(0) != '0'||phone.charAt(1) != '4' ){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Phone number must start with 04. Try again.");

            alert.showAndWait();

            return true;

        }
        return false;
    }



    /*
     * Generate employee ID
     */
    public String generateEmployeeNo(String businessID){
        int count = 1;
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    String loginDetails[] = x.split(":",5);

                    if(loginDetails[0].equals(businessID)){
                        count++;
                    }
                }
                //prints error
            } catch (IOException e) {
                // e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.WARNING,e.toString(),e);
            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
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

    //checks the validiity of time entered
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
    public ArrayList<String> loadInfo(){
        hours = new ArrayList<>();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("workdaysList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    hours.add(x);
                }
                return hours;
                //prints error
            } catch (IOException error) {
                //error.printStackTrace();
                l.Logging();
                LOGGER.log(Level.WARNING,error.toString(),error);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }
            return hours;

            //file cannot be found
        } catch (FileNotFoundException error) {
            // System.out.println(error);
            //error.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,error.toString(),error);
        }

        return hours;

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
        String bId = null;
        String empID = null;
        String day = null;
        try {
            for(int i=0; i < hours.size() ;i++) {

                // printing out each line in the file
                String Details[] = hours.get(i).split(" ", 5);
                bId = Details[0];
                empID = Details[1];
                day = Details[2];


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
        }            catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("Error in storage file");

            l.Logging();
            LOGGER.log(Level.SEVERE,ae.toString(),ae);
            return;

        }
    }

    /*
     * Customer can see their current bookings
     */
    public void viewBookingsCustomer(String username,String b){
        loadCurrentBookings(b);

        //print their current bookings
        for(int i=0;i<currentBookings.size();i++){
            if(currentBookings.get(i).getCustomerID().equals(username)){
                System.out.println("\nDate: " + currentBookings.get(i).getDate() );
                System.out.println("\nDay: " + currentBookings.get(i).getDayBooked() );
                System.out.println("Time: " + currentBookings.get(i).getTimeBooked()  );
                System.out.println("Service: " + currentBookings.get(i).getServiceBooked());
            }

        }
    }


    /*
     * View current bookings and past bookings - choose which one
     */
    public void viewBookings(String b){
        Scanner reader = new Scanner(System.in);
        loadCurrentBookings(b);
        loadPastBookings(b);
        checkBookings();//removes bookings that have occurred
        System.out.println("\n+----------------------------------+");
        System.out.println("|               View               |");
        System.out.println("|             Bookings             |");
        System.out.println("+----------------------------------+");

        System.out.println("\n1. View Current Bookings");
        System.out.println("2. View Past Bookings");
        System.out.println("3. Return to Business menu");
        System.out.print("Choose option (1-3): ");

        while(!reader.hasNextInt()) {
            System.out.println("Error: entered a non integer. Enter a number between 1-3.");
            System.out.print("Enter choice (1-3): ");
            reader.next();
        }

        int choice = reader.nextInt();

        //infinite loop
        while(true){
            if(choice == 1){ //view current bookings
                viewCurrentBookings();
                break;
            }
            else if(choice ==2){ //view past bookings
                viewPastBookings();
                break;
            }
            else if(choice ==3){ //view past bookings

                break;
            }
            else {
                System.out.println("Error."); //fix this later
                break;

            }
        }
    }
    public void checkBookings(){
        currentBookings = insertionSortDate(currentBookings);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,0);

        Date current;
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        try {today = date.parse(date.format(c.getTime()));}catch(ParseException e){}
        for(int j=0; j<currentBookings.size();j++) {
            try {
                current = new SimpleDateFormat("dd/MM/yyyy").parse(currentBookings.get(j).getDate());

                if ((current).before(today)) {
                    pastBookings.add(currentBookings.get(j));
                    currentBookings.remove(j);
                }
                else{j=currentBookings.size()-1;}
                //ordered dates therefore do not nees to search through all
            }catch(ParseException e){}
        }
        }
    /*
     * View current bookings of a business
     */
    public void viewCurrentBookings(){
        ArrayList<String> days = new ArrayList<>();
        for(int i=0; i<currentBookings.size();i++){
            if(!days.contains(currentBookings.get(i).getDayBooked())){
                days.add(currentBookings.get(i).getDayBooked());
            }
        }
        //sort by date then get the next weeks dates
        currentBookings = insertionSortDate(currentBookings);
        days = insertionSort(days);
        for(int i=0; i<days.size();i++){
            System.out.println("~~~~~~~~~~~~~" + days.get(i) + "~~~~~~~~~~~~~");
            for(int j=0; j<currentBookings.size();j++){

                if(currentBookings.get(j).getDayBooked().equals(days.get(i))){
                    System.out.println("----------------------------------------------------");
                    System.out.println("|   " + currentBookings.get(j).getCustomer() );
                    System.out.println("|   " + currentBookings.get(j).getTimeBooked()  );
                    System.out.println("|   " + currentBookings.get(j).getServiceBooked() );
                    System.out.println("----------------------------------------------------");
                }

            }
        }
        days.clear();
        currentBookings.clear();
        pastBookings.clear();
    }

    /*
     * view past bookings
     */
    public void viewPastBookings(){
        ArrayList<String> daysZ = new ArrayList<>();
        for(int i=0; i<pastBookings.size();i++){
            if(!daysZ.contains(pastBookings.get(i).getDayBooked())){
                daysZ.add(pastBookings.get(i).getDayBooked());
            }
        }
        //sort by date then get the next week
        pastBookings = insertionSortDate(pastBookings);
        daysZ = insertionSort(daysZ);
        for(int i=0; i<daysZ.size();i++){
            System.out.println("~~~~~~~~~~~~~" + daysZ.get(i) + "~~~~~~~~~~~~~");
            for(int j=0; j<pastBookings.size();j++){

                if(pastBookings.get(j).getDayBooked().equals(daysZ.get(i))){
                    System.out.println("----------------------------------------------------");
                    System.out.println("|   " + pastBookings.get(j).getCustomer() );
                    System.out.println("|   " + pastBookings.get(j).getTimeBooked()  );
                    System.out.println("|   " + pastBookings.get(j).getServiceBooked() );
                    System.out.println("----------------------------------------------------");
                }

            }
        }
        daysZ.clear();
        currentBookings.clear();
        pastBookings.clear();
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
                    // LOGGER.log(Level.FINEST,ex.toString(),ex); //for testing
                    return true;
                }

            }
        }
        return false;
    }

    /*
     * Print the worktimes of a specific employee or all employee's
     */
    public void printEmployeeWorktimes(String bId, String employeeID){
        Employee emp = new Employee();
        Scanner eID = new Scanner(System.in);
        String empID = employeeID;
        String name = emp.getEmployeeName(bId,empID);
        loadInfo();
        System.out.println("-----------------------------------------");
        System.out.println("The current times "+name+" is working:");
        for(int i=0; i < hours.size() ;i++) {
            try {
                // printing out each line in the file
                String Details[] = hours.get(i).split(" ", 5);


                String e = Details[0];
                String day = Details[2].toLowerCase();
                String start = Details[3];
                String end = Details[4];

                //change day to uppercase
                day = day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();


                if (bId.equals(e)) {
                    System.out.println(day + " " + start + " - " + end);
                }
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }

        }
        System.out.println("-----------------------------------------");

    }



    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private enum DayOfWeek {
        Monday(1),Tuesday(2),Wednesday(3),Thursday(4),Friday(5),Saturday(6),Sunday(7);

        private final int value;

        DayOfWeek(int value) {

            this.value = value;
        }

        public int getValue() {

            return value;
        }

        @Override
        public String toString() {

            return value + "";
        }
    }


    public ArrayList<String> insertionSort(ArrayList<String> days){

        String temp;

        for (int i = 1; i < days.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(DayOfWeek.valueOf(days.get(j)).getValue() < DayOfWeek.valueOf(days.get(j-1)).getValue() ){
                    temp = days.get(j);
                    days.set(j, days.get(j-1));
                    days.set(j-1, temp);
                }
            }
        }
        return days;
    }
    public ArrayList<Bookings> insertionSortDate(ArrayList<Bookings> days){

        Bookings temp;

        for (int i = 1; i < days.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                try {
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(days.get(j-1).getDate());
                    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(days.get(j).getDate());
                    if ((date2).before(date1)) {
                        temp = days.get(j);
                        days.set(j, days.get(j - 1));
                        days.set(j - 1, temp);
                    }
                }catch(ParseException e){}
            }
        }
        return days;
    }




}