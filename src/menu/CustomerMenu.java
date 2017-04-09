package menu;

import BusinessWorkDays.Workday;
import coreFunctions.Driver;
import test.Logging;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class CustomerMenu {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    Login login = new Login();
    Driver driver = new Driver();
    Workday workday = new Workday();

    public void printMenu(String username){

        Scanner reader = new Scanner(System.in);


        /* debug purposes
        for(int i=0; i < login.customerList.size() ;i++){
            System.out.println(login.customerList.get(i).getName());
            System.out.println(login.customerList.get(i).getAddress());
            System.out.println(login.customerList.get(i).getPhoneNo());

        } */

        //infinite loop
        while(true) {
            //print customer menu
            System.out.println("\n+----------------------------------+");
            System.out.println("|           Customer               |");
            System.out.println("|              menu                |");
            System.out.println("+----------------------------------+");

            System.out.println("\n1. Book appointment (view available days/times)");
            System.out.println("2. View Booking(s)");
            System.out.println("3. Log out");

            System.out.print("Enter choice: ");

            while(!reader.hasNextInt()) {
                System.out.println("Error: entered a non integer. Enter a number between 1-8.");
                System.out.print("Enter choice (1-8): ");
                reader.next();
            }

            int choice = reader.nextInt();
            if(choice == 1){
                //the customer will be presented with a menu with a list of business's using
                //the system
                //from there, we grab the business id


                int bID = getBusiness(); //hardcode for one because 1 business
                System.out.println("\n"+login.businessList.get(bID).getName()+ " [opening hours]");
                System.out.println("-----------------------------");
                workday.printFile(login.businessList.get(bID).getUsername());
                System.out.println("\nThe business is open at the above times.");
                System.exit(0);

            }
            if(choice == 2){
                System.out.println("Current Bookings: ");
                driver.viewBookingsCustomer(username); //view current bookings
                System.exit(0);

            }


            if(choice == 3){
                System.out.println("Successfully logged out of the system!");
                System.exit(0);

            }

            else {
                System.out.println("Will do these options later!");
                System.exit(0);

            }


        }



    }

    public int getBusiness(){

        Scanner reader = new Scanner(System.in);

        System.out.println("\n\nWhich business would you like to view/book for?");
        for(int i=1;i<login.businessList.size();i++){
            System.out.println(i+". "+login.businessList.get(i).getName());
        }
        System.out.print("Choose option: ");
        int id = reader.nextInt();
        return id -1;

    }
}
