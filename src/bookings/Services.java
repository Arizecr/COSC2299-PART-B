package bookings;

import coreFunctions.WriteToFile;
import test.Logging;
import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yesmi on 20/04/2017.
 */
public class Services {
    private String bId;
    private String sId;
    private String name;
    private String e;
    private String lengthT;
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    Logging l =new Logging();
    private static ArrayList<Services> serviceList = new ArrayList<>();


    public Services(String bId,String sId,String name,String lengthT,String e) {

        this.bId = bId;
        this.sId = sId;
        this.name = name;
        this.lengthT = lengthT;
        this.e = e ;

    }

    public Services() {

    }
    public void printService(String b,String type) {
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("services.txt"));

            try {
                serviceList = new ArrayList<>();
                ArrayList<String> EOserviceList = new ArrayList<>();
                String x;
                String line = null;
                if(type.equals("c")){
                    line = "|Service ID|Name of Service | length: in Hours and Minutes";
                }
                if(type.equals("b")){
                    line = "|Service ID|Name of Service | length: in Hours and Minutes | Employees ";
                }
                System.out.println(line);


                while ((x = br.readLine()) != null) {
                    EOserviceList = new ArrayList<>();
                    // printing out each line in the file
                    String Details[] = x.split(":", 5);
                    String bid = Details[0];
                    String sid = Details[1];
                    String n = Details[2];
                    String l = Details[3];
                    String e = Details[4];
                    String Time[] = l.split("-", 2);
                    String hours = Time[0];
                    String min = Time[1];
                  /*  String empID[] = e.split(",");
                    for(String emp:empID){
                        EOserviceList.add(emp);
                    }*/
                    Services addS = new Services(bid,sid,n,l,e);
                    serviceList.add(addS);

                    if(type.equals("c")) {
                        line = String.format("|%10s|%16s|%2s hours and %s minutes", sid, n, hours, min);
                    }
                    else if(type.equals("b")){
                        line = String.format("|%10s|%16s|%2s hours and %s minutes       | %s", sid, n, hours, min,e);
                    }
                    System.out.println(line);



                }
                //prints error
            } catch (IOException e) {
                //e.printStackTrace();\
                l.Logging();
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }
            for(int j=0; j < serviceList.size() ;j++){

                System.out.println(serviceList.get(j).e);////////////
            }

            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }
    public void addService(String b){//,String sId,String name, String time
        ArrayList<String> EOserviceList = new ArrayList<>();
        String s = generateServiceNo(); //generate user id
        int sSize = serviceList.size();
        WriteToFile w = new WriteToFile();
        String n = null;
        String len = null;
        String employees = null;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Name of service: ");
            n = reader.nextLine();
        }while(!checkName(n));
        do {
            System.out.print("length in HH-mm ");
            len = reader.nextLine();
        }while(!checkDur(len));
        do {
            System.out.println("employee IDs in the form[eX,eX,eX,...]: ");
            employees = reader.nextLine();
        }while(false);//----------------------------------------------------------- write code to check all these employee ids exist in the business and that none repeat
    /*    String empID[] = employees.split(",");
        for(String emp:empID){
            EOserviceList.add(emp);
        }*/
        Services addS = new Services(b,s,n,len,employees);
        serviceList.add(addS);
        w.WriteToWorkingdayTXT(serviceList.get(sSize).toString(),"services.txt");

        System.out.print("Service added");

        //add in code to add employees to the service
    }

    public void removeService(String b){//,String sId,String name, String time
        WriteToFile w = new WriteToFile();
        String n = null;
        int index;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Service ID: ");
            n = reader.nextLine();
            index = checkID(n);
        }while(index==0);

        serviceList.remove(index-1);

        rewriteToFile(serviceList,"services.txt");
        System.out.print("Service removed");

    }

    public void removeEmployee(String b){//,String sId,String name, String time
        WriteToFile w = new WriteToFile();
        ArrayList<String> eList = new ArrayList<>();
        String n = null;
        String nn = null;
        int index;
        boolean index2;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Service ID: ");
            n = reader.nextLine();
            index = checkID(n);
        }while(index==0);
        do {
            System.out.print("Employee ID: ");
            nn = reader.nextLine();
            index2 = checkEID(index-1,nn);//checks the employee is valid
        }while(!index2);

        String ne = serviceList.get(index-1).e;

        if(ne.contains(nn+",")){ ne = ne.replace(nn+",","");}
        else if(ne.contains(nn)){ ne = ne.replace(","+nn,"");}
        serviceList.get(index-1).e = ne;

        rewriteToFile(serviceList,"services.txt");
        System.out.print("Employee Removed");

    }
    public void addEmployee(String b){//,String sId,String name, String time
        WriteToFile w = new WriteToFile();
        ArrayList<String> eList = new ArrayList<>();
        Employee employee = new Employee();
        String n = null;
        String nn = null;
        int index;
        int index2;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Service ID: ");
            n = reader.nextLine();
            index = checkID(n);
        }while(index==0);
        do {
            System.out.print("Employee ID: ");
            nn = reader.nextLine();

        }while(!checkEID(index-1,nn)&&!employee.checkEmployeeID(b,nn));
        String currentE = serviceList.get(index-1).e;
        currentE += "," + nn;
        serviceList.get(index-1).e = currentE;
        rewriteToFile(serviceList,"services.txt");
        System.out.print("Employee Added to service");




        //add in code to add employees to the service
    }

    public int checkID(String n){
        for(int i=0; i < serviceList.size() ;i++){
            if(n.equals(serviceList.get(i).sId)){
                return i+1;
            }
        }

        return 0;
    }
    public boolean checkEID(int s,String e){
        ArrayList<String> EOserviceList = new ArrayList<>();
        String ne = serviceList.get(s).e;
        String empID[] = ne.split(",");
        for(String emp:empID){
            EOserviceList.add(emp);
        }
        ArrayList<String> emplist = EOserviceList;
        for(int j=0; j < emplist.size() ;j++){
            if(e.equals(emplist.get(j))){
                return true;
            }
        }
        return false;

    }
    public boolean checkName(String n){
        if(n.length()>16){
            System.out.println("name is too long(must be less than 16 characters)");
            return false;
        }
        if(n.length()<=5){
            System.out.println("name is too short(must be more than 5 characters)");
            return false;
        }

        return true;
    }

    public boolean checkDur(String n){
        String Details[] = n.split("-", 2);
        String hours = Details[0];
        String min = Details[1];
        int h = 0;
        int m =0;
        try{
            h = Integer.parseInt(hours);
        } catch (NumberFormatException e) {
            System.out.println("invalid hours");
            return false;
        }
        try{
            m = Integer.parseInt(min);
        } catch (NumberFormatException e) {
            System.out.println("invalid minutes");
            return false;
        }
        if(n.length()>5){
            System.out.println("invalid length");
            return false;
        }
        if(h>8||(h==8&&m>0)){
            System.out.println("cannot be longer than 8 hours");
            return false;
        }
        if(m!=0&&m!=30){
            System.out.println("minutes can only be 0 or 30");
            return false;
        }
        if(h==0&&m==0){
            System.out.println("length of service cannot be 0");
            return false;
        }

        return true;
    }
    /*
    * Generate employee ID
    */
    private String generateServiceNo(){
        int count = 1;
        int largest = serviceList.size()-1;//gets the last in the list
        String c = serviceList.get(largest).sId.substring(1);//gets the number
        //due to deletion this may not be the same as the index of arraylist
        count = Integer.parseInt(c)+1;
        return "s"+count;
    }
    public String toString() {
        String part1=bId + ":" + sId + ":" + name + ":" + lengthT + ":"+e ;
        //System.out.println(emp.size());

        return part1;
    }
    public void rewriteToFile( ArrayList<Services> serviceList,String filename){
        WriteToFile w = new WriteToFile();
        if(serviceList.size()>=0){w.reWriteToWorkingdayTXT(serviceList.get(0).toString(), filename);}
        for(int i=1; i < serviceList.size() ;i++){

            w.WriteToWorkingdayTXT(serviceList.get(i).toString(), filename);
        }
    }

}
