package Menu;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Login {

    public void loginMenu(){


        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("\n\nGreat. Just enter your username and password to log in \n");

        //infinite loop
        while(true){

            System.out.print("Username: ");
            String username = reader.nextLine();

            System.out.print("Password: ");
            String password = reader.nextLine();

            //test if customer login is valid
            if(username.charAt(0) == 'c'){
                if(verifyCustomerLogin(username, password)){

                }
                //customer
            }

            //test if customer login is valid
            if(username.charAt(0) == 'b'){
                if(verifyOwnerLogin(username, password)){

                }
                //owner
            }


            //Login details are not valid, try again
            else{
                System.out.println("Invalid login details. Try again");

            }
        }
    }

    /*
     * Tests whether customer login details are valid
     */
    public boolean verifyCustomerLogin(String username, String password){
        return true;
    }

    /*
     * Tests whether owner login details are valid
     */
    public boolean verifyOwnerLogin(String username, String password){
        return true;
    }



}
