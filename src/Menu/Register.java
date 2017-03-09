package Menu;

import Actor.Customer;
import CoreFunctions.WriteToFile;

import java.util.Scanner;

/**
 * Created by Martin on 5/03/2017.
 */
public class Register {




    public void registerMenu(){
        Login login = new Login();
        Scanner reader = new Scanner(System.in);
        WriteToFile toTxt = new WriteToFile();

        boolean loginSuccessful = false;




        // Logs user into the system
        System.out.println("\n\nRegister your details below. ");
        System.out.println("------------------------------------ \n");



        //infinite loop
        while(true){
            int valid = 0;

            System.out.print("Username (must start with C): ");
            String username = reader.nextLine();
            //remember to append c to the start of username

            System.out.print("Password: ");
            String password = reader.nextLine();



            //test if customer login is valid
            if(username.charAt(0) == 'c' && username.length() <= 15){
                for(int i = 0; i< login.list.size(); i++){
                    if( login.list.get(i).getUsername().equals(username) ){
                        System.out.println("Username already exists. Re-enter valid username");
                        valid++;
                    }


                }

            }


            //Login details are not valid, try again
            else{
                System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");
                valid++;

            }


            if(valid == 0){
                toTxt.WriteToTXT(new Customer(username, password), "customerinfo.txt");
                System.out.println("Succesfully Registered");
                break;
            }
        }




    }



}
