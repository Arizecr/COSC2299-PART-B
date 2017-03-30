package BusinessWorkDays;

import coreFunctions.*;

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
 * Created by Martin on 28/03/2017.
 */
public class Workday

{

    public static ArrayList<Workday> workhours = new ArrayList<>();
    WriteToFile write = new WriteToFile();
    Driver drive = new Driver();

    private String starttime;
    private String endtime;
    private String businessid;
    private String workday;

    public Workday(){}
    public Workday(String businessid, String workday, String starttime, String endtime){
        this.businessid = businessid;
        this.workday = workday;
        this.starttime = starttime;
        this.endtime = endtime;

    }
    public String workD(){

        return workday;
    }
    public String getStarttime(){

        return starttime;
    }
    public String getEndtime(){

    return endtime;
    }
    public String getBId(){

        return businessid;
    }
    public void Details(String b,String d,String s,String end){ BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime = "" ;
        String endtime = "";
        try {


            br = new BufferedReader(new FileReader("businessdaysList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    bId = Details[0];
                    day = Details[1];
                    starttime = Details[2];
                    endtime = Details[3];
                    Workday n = new Workday(bId,day,starttime,endtime);
                    workhours.add(n);
                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }}
    public void readFile(String b,String d,String s,String end){
       Details(b,d,s,end);
        int num= 0;
        for(int i=0; i < workhours.size() ;i++){
            if(b.equals(workhours.get(i).getBId())){
                if(d.equals(workhours.get(i).workD())){
                   num ++;
                    Workday n = new Workday(b,d,s,end);
                    workhours.set(i, n);

                }
            }

        }

        if (num>0){
       drive.loadandWriteNEmployeeWorktimes( b, d, s,end);

    }
        rewriteToFile(workhours);
    }

    public boolean readWork(String b,String d,String s,String end){
        Details(b,d,s,end);
        int count = 0;
        for(int i=0; i < workhours.size() ;i++) {
            if (b.equals(workhours.get(i).getBId())) {
                if (d.equals(workhours.get(i).workD())) {
                    DateFormat time = new SimpleDateFormat("HH:mm");
                    count++;
                    try {

                        Date st = time.parse(s);
                        Date et = time.parse(end);
                        Date Bst = time.parse(workhours.get(i).getStarttime());
                        Date Bet = time.parse(workhours.get(i).getEndtime());


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
            if(count==0){System.out.println("Not Open on " + d);return true;}

        } return false;
    }

    public void printFile(String realbId){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";
        try {


            br = new BufferedReader(new FileReader("businessdaysList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    bId = Details[0];
                    day = Details[1];
                    starttime = Details[2];
                    endtime = Details[3];

                    if(realbId.equals(bId)){System.out.println(day+" " + starttime +" to  "+ endtime );}

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

 /*   public void writeToFile(){
        write.WriteToWorkingdayTXT(toString(), "businessdaysList.txt");

    }*/
    public void rewriteToFile( ArrayList workhours){
        if(workhours.size()>=0){write.reWriteToWorkingdayTXT(workhours.get(0).toString(), "businessdaysList.txt");}
        for(int i=1; i < workhours.size() ;i++){
            write.WriteToWorkingdayTXT(workhours.get(i).toString(), "businessdaysList.txt");
        }
    }

    public String toString(){

        String format = businessid + " " + workday + " " + starttime + " " + endtime;

        return format;
    }


}
