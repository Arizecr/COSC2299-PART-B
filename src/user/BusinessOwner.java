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

            for(int i = 0; i < Login.businessList.size() ; i++){
                if(l.equals(Login.businessList.get(i).getUsername()))
                {
                    bo.add(Login.businessList.get(i));

                }
            }

        }
        return bo;//null business owner
    }
    public String gBN(){return generateBusinessNo();}
    private String generateBusinessNo(){
        int count = 1;
        int largest = Login.businessList.size()-1;//gets the last in the list
        String c = Login.businessList.get(largest).getUsername().substring(1);//gets the number
        //due to deletion this may not be the same as the index of arraylist
        count = Integer.parseInt(c)+1;
        return "b"+count;
    }
    public String getPassword(){

        return password;
    }




}
