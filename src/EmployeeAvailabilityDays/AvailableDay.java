package EmployeeAvailabilityDays;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import coreFunctions.*;

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

    public AvailableDay(String employeeid, String workday, String starttime, String endtime){
        this.employeeid = employeeid;
        this.workday = workday;
        this.starttime = starttime;
        this.endtime = endtime;

    }
    public String getempid(){

        return employeeid;
    }
    public String workD(){

        return workday;
    }
    public String getStarttime(){

        return starttime;
    }
    public String getEndtime() {

        return endtime;
    }
    public void Details(String emp,String d,String s,String end){
        BufferedReader br;
        String empid= "" ;
        String day ="" ;
        String starttime = "" ;
        String endtime = "";

        try {
            br = new BufferedReader(new FileReader("employeeAvailabilityList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    empid = Details[0];
                    day = Details[1];
                    starttime = Details[2];
                    endtime = Details[3];
                    AvailableDay n = new AvailableDay(empid,day,starttime,endtime);
                    availability.add(n);
                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }
            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public void readFile(String emp,String d,String s,String end) {
        Details(emp, d, s, end);
        int num = 0;
        for (int i = 0; i < availability.size(); i++) {
            if (emp.equals(availability.get(i).getempid())) {
                if (d.equals(availability.get(i).workD())) {
                    num++;
                    AvailableDay n = new AvailableDay(emp, d, s, end);
                    availability.set(i, n);

                }
            }
        }

        if (num > 0) {
            drive.loadandWriteNEmployeeWorktimes(emp, d, s, end);

        }
        rewriteToFile(availability);
    }

    public boolean readWork(String emp,String d,String s,String end){
        Details(emp,d,s,end);
        //------------------------------------------------------check if date already exists
        int count = 0;
        for(int i=0; i < availability.size() ;i++) {
            if (emp.equals(availability.get(i).getempid())) {
                if (d.equals(availability.get(i).workD())) {
                    DateFormat time = new SimpleDateFormat("HH:mm");
                    count++;
                    try {

                        Date st = time.parse(s);
                        Date et = time.parse(end);
                        Date Bst = time.parse(availability.get(i).getStarttime());
                        Date Bet = time.parse(availability.get(i).getEndtime());


                        // This makes sure scheduled employee shift is within operating hours of business
                        if (et.after(Bet)) {
                            System.out.println("Can't end after closed");
                            return true;

                        } else if (st.before(Bst)) {
                            System.out.println("Can't Start before open");
                            return true;
                        }

                    } catch (ParseException e) {
                        System.out.println("Invalid Time");
                        return true;

                    }
                }
            }
        }
        if(count==0){System.out.println("Not Open on " + d);return true;}
        return false;
    }

    public void printFile(String realempid){
        BufferedReader br;
        String empid= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";
        try {


            br = new BufferedReader(new FileReader("employeeAvailabilityList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    empid = Details[0];
                    day = Details[1];
                    starttime = Details[2];
                    endtime = Details[3];

                    if(realempid.equals(empid)){System.out.println(day+" " + starttime +" to  "+ endtime );}

                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }
            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }



    public void rewriteToFile( ArrayList availability){
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
