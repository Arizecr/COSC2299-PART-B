package menu;

import BusinessWorkDays.Workday;
import coreFunctions.Driver;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class CustomerMenu {
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
                String bID ="b1";  //hardcore for one because 1 business
                System.out.println("\nRay's Salon [opening hours]");
                System.out.println("-----------------------------");
                workday.printFile(bID);
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
}
