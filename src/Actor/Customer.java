package Actor;

/**
 * Created by Martin on 5/03/2017.
 */
public class Customer {

    // information/details related to a Actor ??
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String phoneNo;
   // private String dob;


    public Customer(String username, String password, String fullName, String address, String phoneNo){  //, String fullName, String dob)
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;
        //this.dob = dob;
    }

    public String toString(){
        return username + ":" +  password + ":" + fullName + ":" + address + ":" + phoneNo;
    }

    public String getUsername(){

        return username;
    }

    public String getPassword(){

        return password;
    }

    public String getName(){

        return fullName;
    }

    public String getAddress(){

        return address;
    }

    public String getPhoneNo(){

        return phoneNo;
    }


}
