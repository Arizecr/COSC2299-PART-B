package Menu;

import CoreFunctions.WriteToFile;
import Customer.Customer;

import java.util.Scanner;

/**
 * Created by Martin on 5/03/2017.
 */
public class Register {

    public void registerMenu(){

        Scanner reader = new Scanner(System.in);
        WriteToFile toTxt = new WriteToFile();

        // Logs user into the system
        System.out.println("\n\nGreat! Just enter your username and password below to register. ");
        System.out.println("---------------------------------------------------------------- \n");

        //infinite loop
        while(true){

            System.out.print("Username: ");
            String username = reader.nextLine();

            System.out.print("Password: ");
            String password = reader.nextLine();

            //test if customer login is valid
            if(username.charAt(0) == 'c'){
                toTxt.WriteToTXT(new Customer(username, password), "customerinfo.txt");
                //customer

                break;
            }

            //test if customer login is valid
            else if(username.charAt(0) == 'b'){

            }


            //Login details are not valid, try again
            else{
                System.out.println("Invalid Registry details. Try again");

            }
        }

        System.out.println("Succesfully Registered");


    }



}
