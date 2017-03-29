package BusinessWorkDays;

import coreFunctions.WriteToFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Martin on 28/03/2017.
 */
public class Workday

{

    public static ArrayList<Workday> workhours = new ArrayList<>();
    WriteToFile write = new WriteToFile();

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
    public String getBId(){

        return businessid;
    }
    public void readFile(String b,String d,String s,String end){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime ;
        String endtime;
        try {


            br = new BufferedReader(new FileReader("businessdaysList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(" ",4);
                   bId = loginDetails[0];
                    day = loginDetails[1];
                   starttime = loginDetails[2];
                    endtime = loginDetails[3];
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
        }
        int num= 0;
        int num2=0;
        for(int i=0; i < workhours.size() ;i++){
            if(b.equals(workhours.get(i).getBId())){
                if(d.equals(workhours.get(i).workD())){
                   num ++;

                }
            }

        }
        if(num==0){
            this.starttime = s;
            this.endtime=end;
            this.businessid=b;
            this.workday =d;
            writeToFile();
        }
        else if (num>0){System.out.println("this date will be overwritten");}
    }
    public void printFile(String realbId){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime ;
        String endtime;
        try {


            br = new BufferedReader(new FileReader("businessdaysList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(" ",4);
                    bId = loginDetails[0];
                    day = loginDetails[1];
                    starttime = loginDetails[2];
                    endtime = loginDetails[3];

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

    public void writeToFile(){
        write.WriteToWorkingdayTXT(toString(), "businessdaysList.txt");

    }

    public String toString(){

        String format = businessid + " " + workday + " " + starttime + " " + endtime;

        return format;
    }


}
