package Menu;

import Actor.Customer;
import Actor.Business;
import java.io.*;
import java.util.*;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Login {
    public static ArrayList<Customer> list = new ArrayList<>();
    public static ArrayList<Business> ownerList = new ArrayList<>();

    public void loginMenu(){

        loadOwnerInformation();

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
            testLogin(username,password);
        }
    }
    public void testLogin(String username, String password){
        CustomerMenu customer = new CustomerMenu();
        //test if customer login is valid
        if(username.charAt(0) == 'c'){
            if(verifyLoginDetails("customer",username, password)){
                customer.printMenu();
                System.exit(0);
            }

        }

        //test if customer login is valid
        if(username.charAt(0) == 'b'){
            if(verifyLoginDetails("owner",username, password)){
                System.out.println("owner login works!\n");

                System.exit(0);
            }
            System.out.println("Invalid login details. Details do not exist in system.");

        }


        //Login details are not valid, try again
        else{
            System.out.println("Invalid login details. Try again");

        }
    }

    /*
     * Load customer data
     */

    public void loadCustomerInformation(){
        BufferedReader br;
        try {


                br = new BufferedReader(new FileReader("customerinfo.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",2);
                    String username = loginDetails[0];
                    String password = loginDetails[1];
                    Customer course = new Customer(username, password);
                    list.add(course);
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

    }
    public void getOwnerinfo(){
        loadOwnerInformation();
    }

    /*
     * Load owner information
     */
    private void loadOwnerInformation(){
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("business.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",2);
                    String username = loginDetails[0];
                    String password = loginDetails[1];
                    Business ownerInfo = new Business(username, password);
                    ownerList.add(ownerInfo);
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

    }


    /*
     * Tests whether customer login details are valid
     */
    public boolean getVerification(String type, String username, String password){
        return verifyLoginDetails(type,username,password);
    }
    private boolean verifyLoginDetails(String type, String username, String password) {

        if (type.equals("customer")){//verify customer
            for(int i=0; i < list.size() ;i++){
                if(username.equals(list.get(i).getUsername())){
                    if(password.equals(list.get(i).getPassword())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }

            }

            return false;

        }

        else{ //verify business owner
            for(int i=0; i < ownerList.size() ;i++){
                if(username.equals(ownerList.get(i).getUsername())){
                    if(password.equals(ownerList.get(i).getPassword())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }

            }

            return false;

        }



    }





}
