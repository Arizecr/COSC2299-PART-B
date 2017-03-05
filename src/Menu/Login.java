package Menu;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Login {

    public void loginMenu(){
        System.out.println("test");

        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("Welcome to the system! Enter your details below. \n");

        //infinite loop
        while(true){

            System.out.print("Username: ");
            String username = reader.nextLine();

            System.out.print("Password: ");
            String password = reader.nextLine();

            //Login details are valid

            if(username.charAt(0) == 'c'){
                if(verifyCustomerLogin(username, password)){

                }
                //customer
            }

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

    public boolean verifyCustomerLogin(String username, String password){
        return true;
    }

    public boolean verifyOwnerLogin(String username, String password){
        return true;
    }



}
