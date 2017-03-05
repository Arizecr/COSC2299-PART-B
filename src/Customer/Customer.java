package Customer;

/**
 * Created by Martin on 5/03/2017.
 */
public class Customer {

    // information/details related to a Customer ??
    private String username;
    private String password;
    //private String fullName;
   // private String dob;


    public Customer(String username, String password){  //, String fullName, String dob)
        this.username = username;
        this.password = password;
        //this.fullName = fullName;
        //this.dob = dob;
    }

    public String toString(){
        return username + ":" +  password + "\n"; //  + ":" + fullName +  ":" + dob;
    }


}
