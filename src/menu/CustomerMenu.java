package menu;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class CustomerMenu {
    Login login = new Login();

    public void printMenu(){

        Scanner reader = new Scanner(System.in);

        //print customer menu
        System.out.println("\n+----------------------------------+");
        System.out.println("|           Customer               |");
        System.out.println("|              menu                |");
        System.out.println("+----------------------------------+");

        System.out.println("\n1. Book appointment");
        System.out.println("2. View Booking(s)");
        System.out.println("3. Log out");

        /* debug purposes
        for(int i=0; i < login.customerList.size() ;i++){
            System.out.println(login.customerList.get(i).getName());
            System.out.println(login.customerList.get(i).getAddress());
            System.out.println(login.customerList.get(i).getPhoneNo());

        } */

        //infinite loop
        while(true) {

            System.out.print("Enter choice: ");
            int choice = reader.nextInt();

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
