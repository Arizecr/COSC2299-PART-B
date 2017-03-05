import Menu.Login;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Main {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        Login login = new Login();

        //display main page
        System.out.println("\n+----------------------------------+");
        System.out.println("|           Welcome to the         |");
        System.out.println("|               system             |");
        System.out.println("+----------------------------------+");
        System.out.println("Would you like to login or register an account? (l/r)");
        System.out.print("Enter choice: ");
        String choice = reader.nextLine();


        //login menu
        if(choice.charAt(0) == 'l'){
            //user chooses to log in
            login.loginMenu();
        }

        //registration menu

    }
}
