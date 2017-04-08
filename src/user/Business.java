package user;
import userBase.User;

/**
 * Created by xemorth on 6/03/2017.
 */
public class Business extends User{

    private String businessName;


    public Business(String username, String password, String fullName, String address, String phoneNo, String businessName){
        super(username, password, fullName, address, phoneNo);
        this.businessName = businessName;
    }

    public String getBusinessName(){
        return businessName;
    }




}
