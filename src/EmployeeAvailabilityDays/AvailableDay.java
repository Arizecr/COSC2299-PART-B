package EmployeeAvailabilityDays;

import BusinessWorkDays.Workday;
import coreFunctions.Driver;
import coreFunctions.WriteToFile;
import menu.BusinessMenu;
import test.Logging;
import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by asus on 30-Mar-17.
 */
public class AvailableDay {

    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    WriteToFile write = new WriteToFile();
    Driver drive = new Driver();

    private String starttime;
    private String endtime;
    private String employeeid;
    private String workday;
    private String bid;

    public AvailableDay(){};

    Employee e = new Employee();
    //list for worker availability
    public static ArrayList<String> availability = new ArrayList<>();
    Logging l =new Logging();

    /*
     * Load list for worker availability
     */
    public ArrayList<String> loadInfo(){
        availability = new ArrayList<>();


        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader("employeeAvailabilityList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    availability.add(x);
                }
                //prints error
            } catch (IOException error) {
                //error.printStackTrace();
                l.Logging();
                LOGGER.log(Level.WARNING,error.toString(),error);
            }
            //file cannot be found
        } catch (FileNotFoundException error) {
            l.Logging();
            // System.out.println(error);
            LOGGER.log(Level.WARNING,error.toString(),error);
        }

        return availability;
    }

    /*
     * Print worker availability
     */
    public void printFile(String bId,String eId){
        loadInfo();
        String bID= "" ;
        String eID= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";
        String name = "";

        for(int i=0; i < availability.size() ;i++){

            String Details[] = availability.get(i).split(" ",5);
            bID = Details[0];
            eID = Details[1];
            day = Details[2];
            day = day.substring(0,1).toUpperCase() + day.substring(1);
            starttime = Details[3];
            endtime = Details[4];
            name = e.getEmployeeName(bId,eID);
            if(bID.equals(bId)&&eID.equals(eId)){System.out.println(day+" " + starttime +" to  "+ endtime );}
            if((bID.equals(bId)&&eId.equals("all"))){System.out.println(name+" ("+ eID +"): "+day+" " + starttime +" to  "+ endtime );}

        }
    }
    public void addEmployeeAvailability(String busId,String eID, String day, String start, String end){
        loadInfo();

        String x = busId + " " +eID + " " + day + " " + start + " " + end;
        if(!editAv(busId,eID,day.toLowerCase(),start,end,x)){
            availability.add(x);
        }
        write.rewriteToFile(availability, "employeeAvailabilityList.txt");

    }


    public boolean editAv(String busId,String eID, String day, String start, String end, String x){
        for(int i=0; i<availability.size(); i++){
            System.out.println(availability.get(i));
            if(availability.get(i).contains(busId) && availability.get(i).contains(eID) && availability.get(i).contains(day)){
                System.out.println("S");
                availability.remove(i);
                availability.add(x);
                return false;
            }
        }


        return true;
    }
    public boolean checkDay(String b, String e, String d){
        loadInfo();
        String bID= "" ;
        String eID= "" ;
        String day ="" ;

        for(int i=0; i < availability.size() ;i++){

            String Details[] = availability.get(i).split(" ",5);
            bID = Details[0];
            eID = Details[1];
            day = Details[2];

            if(bID.equals(b)&&eID.equals(e)&&day.equals(d)){return true;}
        }

        return false;
    }

    /*
     *
     */
    //check valid and not already existing
    public boolean checkFile(String b,String emp, String d,String st,String en)
    {
        loadInfo();
        String bID= "" ;
        String empid= "" ;
        String day ="" ;
        String start ="";
        String end="";
        int count = 0;
        int inFile = 0;
        for(int i=0; i < availability.size() ;i++){

            String Details[] = availability.get(i).split(" ",5);
            bID = Details[0];
            empid = Details[1];
            day = Details[2].toLowerCase();
            start = Details[3];
            end = Details[4];
            if(bID.equals(b)&&empid.equals(emp)&&day.equals(d.toLowerCase())){
                DateFormat time = new SimpleDateFormat("HH:mm");
                inFile++;
                try {
                    Date sd = time.parse(st);
                    Date ed = time.parse(en);
                    Date Asd = time.parse(start);
                    Date Aed = time.parse(end);

                    // This makes sure scheduled employee shift is within operating availability of business
                    if (ed.after(Aed)) {
                        System.out.println("Error: this shift is not within the employees availability");
                        count++;

                    } else if (sd.before(Asd)) {
                        System.out.println("Error: this shift is not within the employees availability");
                        count++;

                    }
                    //   else {return false;}
                } catch (ParseException e) {
                    System.out.println("Invalid Time");
                    count ++;
                }
            }

        }
        if(inFile==0){
            System.out.println("Error: This employee is unavailable on this day & time");
            return true;
        }

        if(count>0){return true;}//invalid availability exists
        return false;
    }


    public String toString(){

        String format = bid + "" + employeeid + " " + workday + " " + starttime + " " + endtime;

        return format;
    }



}
