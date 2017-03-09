package Menu;

import Actor.Customer;
import CoreFunctions.WriteToFile;

import java.util.Scanner;

/**
 * Created by Martin on 5/03/2017.
 */
public class Register {

    public void registerMenu(){

        Scanner reader = new Scanner(System.in);
        WriteToFile toTxt = new WriteToFile();

        // Logs user into the system
        System.out.println("\n\nRegister your details below. ");
        System.out.println("------------------------------------ \n");

        //infinite loop
        while(true){

            System.out.print("Username [Must start C]: ");
            String username = reader.nextLine();
            //remember to append c to the start of username

            System.out.print("Password: ");
            String password = reader.nextLine();



            //test if customer login is valid
            if(username.charAt(0) == 'c' && username.length() <= 15){

                toTxt.WriteToTXT(new Customer(username, password), "customerinfo.txt");
                //customer

                break;
            }


            //Login details are not valid, try again
            else{
                System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");

            }
        }

        System.out.println("Succesfully Registered");


    }



}
