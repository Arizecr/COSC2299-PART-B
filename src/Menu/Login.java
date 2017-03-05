package Menu;

import Customer.Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Login {

    public void loginMenu(){
        ArrayList<Customer> list = new ArrayList<Customer>();

        //load textfile to customer and business owner class
        //loadInformation();

        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("\n\nGreat! Just enter your username and password below to log in. ");
        System.out.println("---------------------------------------------------------------- \n");

        //infinite loop
        while(true){

            System.out.print("Username: ");
            String username = reader.nextLine();

            System.out.print("Password: ");
            String password = reader.nextLine();

            //test if customer login is valid
            if(username.charAt(0) == 'c'){
                if(verifyLoginDetails(username, password)){
                    System.out.println("customer login works!\n");
                    System.exit(0);
                }

            }

            //test if customer login is valid
            if(username.charAt(0) == 'b'){
                if(verifyLoginDetails(username, password)){
                    System.out.println("owner login works!\n");

                    System.exit(0);
                }

            }


            //Login details are not valid, try again
            else{
                System.out.println("Invalid login details. Try again");

            }
        }
    }

    public void loadInformation(){

    }
    /*
     * Tests whether customer login details are valid
     */
    public boolean verifyLoginDetails(String username, String password){
        BufferedReader br;
        try {

            //business
            if(username.charAt(0) == 'b'){
                br = new BufferedReader(new FileReader("business.txt"));
            }

            //customer
            else {
                br = new BufferedReader(new FileReader("customerinfo.txt"));
            }


            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",2);
                    if(username.equals(loginDetails[0])){

                        if(password.equals(loginDetails[1])){

                            return true;
                        }
                        else{

                            return false;

                        }
                    }
                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }

            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return false;


    }



}
