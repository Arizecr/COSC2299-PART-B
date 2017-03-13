package Menu;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class BusinessMenu {

    public void printMenu(){
        Scanner reader = new Scanner(System.in);
        System.out.println("\n+----------------------------------+");
        System.out.println("|           Business               |");
        System.out.println("|              Menu                |");
        System.out.println("+----------------------------------+");

        System.out.println("3. Log out");

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
