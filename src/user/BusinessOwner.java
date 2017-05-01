package user;

import menu.Login;

import java.util.ArrayList;

/**
 * Created by xemorth on 6/03/2017.
 */
public class BusinessOwner{

    protected String username;
    protected String password;
    protected String allB;
    Login b = new Login();

    public BusinessOwner(String username, String password, String allB){
        this.username = username;
        this.password = password;
        this.allB = allB;
    }

    public String toString(){
        return username + ":" +  password + ":" +allB;
    }

    public String getUsername(){

        return username;
    }
    public ArrayList<Business> getAllB(){

        ArrayList<Business> bo = new ArrayList<>();
        String loginDetails[] = allB.split(",");
        for(String l:loginDetails){

            for(int i=0; i < b.businessList.size() ;i++){
                if(l.equals(b.businessList.get(i).getUsername()))
                {
                    bo.add(b.businessList.get(i));

                }
            }

        }
        return bo;//null business owner
    }

    public String getPassword(){

        return password;
    }




}
