package Actor;

/**
 * Created by xemorth on 6/03/2017.
 */
public class Business {
    // information/details related to a Actor ??
    private String username;
    private String password;
    private String businessName;
    private String fullName;
    private String address;
    private String phoneNo;



    public Business(String username, String password, String businessName, String fullName, String address, String phoneNo){  //, String fullName, String dob)
        this.username = username;
        this.password = password;
        this.businessName = businessName;
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;

    }

    public String toString(){
        return username + ":" +  password + ":" +  businessName + ":" +  fullName + ":" +  address + ":" +  phoneNo + "\n";
    }

    public String getUsername(){

        return username;
    }

    public String getPassword(){

        return password;
    }

    public String getBusinessName(){

        return businessName;
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
