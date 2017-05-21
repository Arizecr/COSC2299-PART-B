package user;
import userBase.User;

/**
 * Created by xemorth on 6/03/2017.
 */
public class Business extends User{

    private String businessName;


    public Business(String username, String password, String businessName, String fullName, String address, String phoneNo){
        super(username, password, fullName, address, phoneNo);
        this.businessName = businessName;
    }

    public String getBusinessName(){
        return businessName;
    }


    public String toString() {

        return username + ":" + password + ":" + businessName + ":" + fullName + ":" + address + ":" + phoneNo;
    }

}
