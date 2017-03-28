package menu;
import BusinessWorkDays.Workday;
import coreFunctions.Driver;
import user.Business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Gabrielle on 5/03/2017.
 * test
 */
public class BusinessMenu {
    Login login = new Login();
    Driver driver = new Driver();
    String starttime;
    String endtime;






    public void printMenu(String bID){
        Scanner reader = new Scanner(System.in);
        Scanner hours = new Scanner(System.in);
        Scanner workdayreader = new Scanner(System.in);
        int command;
        GregorianCalendar cur = new GregorianCalendar();
        Scanner eID = new Scanner(System.in);
        String firstdate;
        String empID;

        ArrayList<String> workday = new ArrayList<>(7);
        ArrayList<String> chosendays = new ArrayList<>(7);




        /* debug purposes
        for(int i=0; i < login.businessList.size() ;i++){
            System.out.println(login.businessList.get(i).getName());
            System.out.println(login.businessList.get(i).getAddress());
            System.out.println(login.businessList.get(i).getPhoneNo());

        } */

        //infinite loop
        while(true) {
            /* adds into arraylist */
            workday.add("Monday");
            workday.add("Tuesday");
            workday.add("Wednesday");
            workday.add("Thursday");
            workday.add("Friday");
            workday.add("Saturday");
            workday.add("Sunday");

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
                boolean validwork = true;


                while(valid){

                    while(true){
                        try{
                            System.out.println("Enter Start Time");
                            starttime = hours.nextLine();

                            if(Integer.parseInt(starttime) > 24 ||Integer.parseInt(starttime) <0){
                                System.out.println("Invalid Start Times");
                                continue;
                            }else{
                                System.out.println("Enter End Time");
                                endtime = hours.nextLine();
                                if(Integer.parseInt(endtime) <0 || Integer.parseInt(endtime) > 24 || Integer.parseInt(endtime) < Integer.parseInt(starttime)){
                                    System.out.println("Invalid Start Times");
                                    continue;
                                }
                            }

                            break;
                        }
                        catch (NumberFormatException e){
                            System.out.println("Invalid Times");

                        }




                    }


                    while(validwork){

                        for(int i=0; i< workday.size(); i++){
                            System.out.println((i+1) + ". " + workday.get(i));

                        }
                        if(workday.size() == 0){
                            break;
                        }
                        System.out.println("0. Exit");

                        command = workdayreader.nextInt();
                        /*
                        do{
                        System.out.print("Enter employee ID:");
                        empID = eID.nextLine();
                        }while(!checkEmployeeID(empID));

*/
                        if(command == 0 && workday.size() < 7){
                            validwork = false;
                        }

                        else if(command == 1 ){
                            chosendays.add(workday.get(0));
                            workday.remove(0);

                        }
                        else if(command == 2  && workday.size()>=2 ){
                            chosendays.add(workday.get(1));
                            workday.remove(1);
                        }
                        else if(command == 3  && workday.size()>=3 ){
                            chosendays.add(workday.get(2));
                            workday.remove(2);
                        }
                        else if(command == 4 && workday.size()>=4 ){
                            chosendays.add(workday.get(3));
                            workday.remove(3);
                        }
                        else if(command == 5 && workday.size()>=5){
                            chosendays.add(workday.get(4));
                            workday.remove(4);
                        }
                        else if(command == 6 && workday.size()>=6){
                            chosendays.add(workday.get(5));
                            workday.remove(5);
                        }
                        else if(command == 7 && workday.size()>=7){
                            chosendays.add(workday.get(6));
                            workday.remove(6);
                        }
                        else{
                            System.out.println("Invalid input");
                        }

                    }
                    Workday createWorkdays = new Workday(bID, chosendays,starttime,endtime);

                    createWorkdays.writeToFile();
                    workday.clear();
                    break;
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
