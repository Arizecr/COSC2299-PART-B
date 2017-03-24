package userBase;
/**
 * Created by xemorth on 24/03/2017.
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String fullName;
    protected String address;
    protected String phoneNo;

    public User(String username, String password, String fullName, String address, String phoneNo){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    @Override
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
