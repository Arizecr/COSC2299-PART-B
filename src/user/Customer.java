package user;

import userBase.User;

/**
 * Created by Martin on 5/03/2017.
 */
public class Customer extends User{
String businessID;
    public Customer(String businessID,String username, String password, String fullName, String address, String phoneNo){
        super(username, password, fullName, address, phoneNo);
        this.businessID = businessID;
    }

    public String toString(){
        return businessID + ":" +username + ":" +  password + ":" + fullName + ":" + address + ":" + phoneNo;
    }
    public String getBusiness(){

        return this.businessID;
    }

}
