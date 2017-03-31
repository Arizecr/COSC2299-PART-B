package EmployeeAvailabilityDays;

import coreFunctions.Driver;
import coreFunctions.WriteToFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asus on 30-Mar-17.
 */
public class AvailableDay {

    public static ArrayList<AvailableDay> availability = new ArrayList<>();
    WriteToFile write = new WriteToFile();
    Driver drive = new Driver();

    private String starttime;
    private String endtime;
    private String employeeid;
    private String workday;

    public AvailableDay(){};


    public static ArrayList<String> hours = new ArrayList<>();
    public void loadInfo(){
        hours = new ArrayList<>();
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeAvailabilityList.txt"));

            try {
                String x;

                while ( (x = br.readLine()) != null ) {
                    hours.add(x);

                }
                //prints error
            } catch (IOException error) {
                error.printStackTrace();
            }


            //file cannot be found
        } catch (FileNotFoundException error) {
            System.out.println(error);
            error.printStackTrace();
        }

    }

    public void printFile(String bId){
        loadInfo();
        String bID= "" ;
        String empid= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";

        for(int i=0; i < hours.size() ;i++){

            String Details[] = hours.get(i).split(" ",5);
            bID = Details[0];
            empid = Details[1];
            day = Details[2];
            starttime = Details[3];
            endtime = Details[4];
            if(bID.equals(bId)){System.out.println(empid+" "+day+" " + starttime +" to  "+ endtime );}

        }


    }

    public boolean checkFile(String b,String emp, String d,String st,String en)
    {
        loadInfo();
        String bID= "" ;
        String empid= "" ;
        String day ="" ;
        String start ="";
        String end="";
        int count = 0;
        for(int i=0; i < hours.size() ;i++){

                String Details[] = hours.get(i).split(" ",5);
                bID = Details[0];
                empid = Details[1];
                day = Details[2];
                start = Details[3];
                end = Details[4];
                if(bID.equals(b)&&empid.equals(emp)&&day.equals(d)){
                    DateFormat time = new SimpleDateFormat("HH:mm");

                    try {
                        Date sd = time.parse(st);
                        Date ed = time.parse(en);
                        Date Asd = time.parse(start);
                        Date Aed = time.parse(end);

                        // This makes sure scheduled employee shift is within operating hours of business
                        if (ed.after(Aed)) {
                            System.out.println("Employee unavailable");
                            count++;

                        } else if (sd.before(Asd)) {
                            System.out.println("Employee unavailable");
                            count++;

                        }
                        else {return false;}
                    } catch (ParseException e) {
                        System.out.println("Invalid Time");
                        count ++;
                    }
                }

        }
        if(count>0){return true;}
        return false;
    }

    public void rewriteToFile(ArrayList availability){
        if(availability.size()>=0){write.reWriteToWorkingdayTXT(availability.get(0).toString(), "employeeAvailabilityList.txt");}
        for(int i=1; i < availability.size() ;i++){
            write.WriteToWorkingdayTXT(availability.get(i).toString(), "employeeAvailabilityList.txt");
        }
    }

    public String toString(){

        String format = employeeid + " " + workday + " " + starttime + " " + endtime;

        return format;
    }



}
