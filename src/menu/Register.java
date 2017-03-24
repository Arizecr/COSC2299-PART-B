package menu;

import user.Customer;
import coreFunctions.WriteToFile;

import java.util.Scanner;

/**
 * Created by Martin on 5/03/2017.
 */
public class Register {


/*
 * Customer is presented with the registration menu
 */
    public void registerMenu(){
        String username;

        Scanner reader = new Scanner(System.in);
        WriteToFile toTxt = new WriteToFile();

        boolean loginSuccessful = false;




        // Logs user into the system
        System.out.println("\n\nRegister your details below. ");
        System.out.println("------------------------------------ \n");



        //infinite loop
        while(true){
            int valid = 0;

            while(true){

                System.out.print("Username (must start with C): ");
                username = reader.nextLine();
                if(testUsername(username)){
                    System.out.println("Username Valid\n");
                    break;

                }
                //remember to append c to the start of username
            }


            System.out.print("Password: ");
            String password = reader.nextLine();

            System.out.print("Name: ");
            String name = reader.nextLine();

            System.out.print("Address: ");
            String address = reader.nextLine();

            System.out.print("Mobile: ");
            String mobile = reader.nextLine();


            valid = testRegister(password, name, address, mobile);
            if(valid == 0){
                toTxt.WriteToTXT(new Customer(username, password, name, address, mobile), "customerinfo.txt");
                System.out.println("Succesfully Registered");
                break;
            }
        }

    }


    public int testReg(String password, String name, String address, String mobile){
        return testRegister(password, name, address, mobile);
    }
    public boolean testUser(String username){
        return testUsername(username);
    }
    private boolean testUsername(String username){
        Login login = new Login();
        //isEmpty just checks for null
        if(username.isEmpty() || (username.charAt(0) != 'c' )){

            System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");
            return false;

        }


        if((username.charAt(0) == 'c') && (username.length()<= 15) && (username.length()>1)){

            for(int i = 0; i< login.customerList.size(); i++) {

                if (login.customerList.get(i).getUsername().equals(username)) {
                    System.out.println("Username already exists. Re-enter valid username\n");
                    return false;
                }
            }


        }
        else{
            return false;
        }


        return true;
    }


    private int testRegister(String password, String name, String address, String mobile){
        //Login login = new Login();
        int valid = 0;
        //test if customer login is valid

        /*
        //isEmpty just checks for null
        if(username.isEmpty() || username.charAt(0) != 'c' ){

            System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");
            return ++valid;

        }


        if((username.charAt(0) == 'c') && (username.length()<= 15) && (username.length()>1)){

            for(int i = 0; i< login.customerList.size(); i++) {

                if (login.customerList.get(i).getUsername().equals(username)) {
                    System.out.println("Username already exists. Re-enter valid username");
                    return ++valid;
                }
            }


        }
        else return ++valid;

        */

        if(name.isEmpty() || (name.length() < 1)  ){

            System.out.println("Invalid Name");
            return ++valid;


        }

        if(address.isEmpty() || (address.length() < 1)){
            System.out.println("Invalid Address");
            return ++valid;

        }

        if(mobile.isEmpty() || (mobile.length() != 10) ){
            System.out.println("Invalid Mobile");
            return ++valid;

        }


        if( password.isEmpty()) {
            System.out.println("Invalid Password");
            return ++valid;
        }

            //Login details are not valid, try again
        /*
        else{


        }
        */

        // if it makes it to this valid, register success :D
        return valid;
    }



}