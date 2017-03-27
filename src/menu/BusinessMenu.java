package menu;
import coreFunctions.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 * test
 */
public class BusinessMenu {
    Login login = new Login();
    Driver driver = new Driver();

    public void printMenu(){
        Scanner reader = new Scanner(System.in);
        Scanner datereader = new Scanner(System.in);
        GregorianCalendar cur = new GregorianCalendar();
        Scanner eID = new Scanner(System.in);
        String firstdate;
        String empID;



        /* debug purposes
        for(int i=0; i < login.businessList.size() ;i++){
            System.out.println(login.businessList.get(i).getName());
            System.out.println(login.businessList.get(i).getAddress());
            System.out.println(login.businessList.get(i).getPhoneNo());

        } */

        //infinite loop
        while(true) {
            //print business menu
            System.out.println("\n+----------------------------------+");
            System.out.println("|           Business               |");
            System.out.println("|              menu                |");
            System.out.println("+----------------------------------+");

            System.out.println("1. Add Employee");
            System.out.println("2. Add working days/times");
            System.out.println("3. View summaries of bookings");
            System.out.println("4. View new Bookings");
            System.out.println("5. Show worker availability");
            System.out.println("6. Log out");

            System.out.print("Enter choice: ");
            int choice = reader.nextInt();

            if(choice == 1){
                driver.addEmployee();
                continue;
            }

            if(choice == 2){
                boolean valid = true;

                while(valid){
                    while(true){
                        do{
                        System.out.print("Enter employee ID:");
                        empID = eID.nextLine();
                        }while(!checkEmployeeID(empID));

                        System.out.print("Enter Day(s):");
                        firstdate = datereader.nextLine();

                        String[] dateparts = firstdate.split("/");
                        try{
                            try{
                                int check = Integer.parseInt(dateparts[1]) - 1;
                            }catch (IndexOutOfBoundsException e){
                                System.out.println("Invalid");
                                continue;
                            }
                            int check = Integer.parseInt(dateparts[1]) - 1;
                            int monthRestriction = cur.MONTH+1;
                            /*
                            System.out.println("Month inputted " + check);
                            System.out.println("Month Restriction " + monthRestriction);
                            */
                            if(check == -1 || check > monthRestriction){
                                System.out.println("Work days can only be booked within a month");
                                continue;
                            }
                            break;


                        }catch(NumberFormatException e){
                            System.out.println("Invalid Date");
                        }
                    }

                    System.out.print("Start Time (hh:mm:ss)");
                    String starttime = datereader.nextLine();
                    System.out.print("End Time (hh:mm:ss)");
                    String endtime = datereader.nextLine();
                    valid = Worktimes(firstdate,starttime,endtime);

                }
            }

            else if(choice == 6){
                System.out.println("Successfully logged out of the system!");
                System.exit(0);
            }

            else {
                System.out.println("Will do these options later!");
                System.exit(0);

            }


        }

    }


    private boolean Worktimes(String firstdate,String starttime,String endtime){
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");

        String dateNtime = firstdate + "|"+ starttime;
        String endNTime = firstdate + "|" + endtime;

        try{
            Date currDate =  new Date();
            Date startdate = dateformat2.parse(dateNtime);
            Date enddate = dateformat2.parse(endNTime);

            long diff = enddate.getTime() - startdate.getTime();
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHM = (diffHours*60)+diffMinutes;

                        /* If needed
                        long diffSeconds = diff / 1000 % 60;
                        long diffMinutes = diff / (60 * 1000) % 60;
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        */

            // This makes sure scheduled work day CANNOT be before the current time and date, Ending work time must not be before start time or equal.
            if(startdate.before(currDate)){

                System.out.println("Going back in time");
                return true;
            }else if(!enddate.after(startdate)){
                System.out.println("Can't Start after its ended");
                return true;

            }else if(startdate.equals(enddate)){
                System.out.println("Can't Start and end at same time");
                return true;
            }

            if(diffHours == 0 || diffHours > 8|| diffHM>480){
                System.out.println("selected "+diffHours + " hours and "+diffMinutes+" minutes");
                System.out.println("Work day must be in range between 1 to 8 hours");
                return true;

            }


            //System.out.println(diffHours);
            System.out.println("The working time of:\t\t\t " + startdate + "\nTil:\t\t\t\t\t\t\t " + enddate);

            driver.addWorkdays(dateNtime, endNTime);
            return false;

        }catch(ParseException e){
            System.out.println("Invalid Date/Time");
            return true;

        }
    }
    public  boolean Workt(String firstdate,String firsttime,String endtime){return Worktimes(firstdate,firsttime,endtime);}


    public boolean checkEmployeeID(String empID){

        if (empID.equals("e1") || empID.equals("e2")){
            return true;
        }

        else
            return false;

    }

}
