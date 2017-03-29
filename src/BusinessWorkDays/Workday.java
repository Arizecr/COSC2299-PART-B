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
    int tmp;
    public static ArrayList<Workday> workhours = new ArrayList<>();
    String sortedString ="";
    WriteToFile write = new WriteToFile();
    public enum DayOfWeek {
        Monday(1),Tuesday(2),Wednesday(3),Thursday(4),Friday(5),Saturday(6),Sunday(7);

        private final int value;

        DayOfWeek(int value) {

            this.value = value;
        }

        public int getValue() {

            return value;
        }

        @Override
        public String toString() {

            return value + "";
        }
    }




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
        for(int i=0; i < workhours.size() ;i++){
            if(b.equals(workhours.get(i).getBId())){
                if(!d.equals(workhours.get(i).workD())){
                    this.starttime = s;
                    this.endtime=end;
                    this.businessid=b;
                    this.workday =d;
                    writeToFile();
                }
            }

        }
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

   /* public void sort(){
        int i =0 ;
        int j;
        DayOfWeek curr;

        while(i < workdays.size() ){
            curr = DayOfWeek.valueOf(workdays.get(i));
            j = i+1;
            tmp = i;
            while(j < workdays.size()){
                if(DayOfWeek.valueOf(workdays.get(j)).getValue() <= curr.getValue()){
                    tmp = j;
                }
                j++;
            }

            if(DayOfWeek.valueOf(workdays.get(tmp)).getValue() <= DayOfWeek.valueOf(workdays.get(i)).getValue()){
                Collections.swap(workdays, i, tmp);

            }
            i++;

        }


    }

    public void getWorkdays(){
        sort();
        for(int i=0; i< workdays.size(); i++){
            System.out.println(workdays.get(i));
        }
    }*/

    public void writeToFile(){
        write.WriteToWorkingdayTXT(toString(), "businessdaysList.txt");

    }



    public String toString(){


       /* for(int i=0; i< workdays.size(); i++){
            if(i == workdays.size()-1){
                sortedString = sortedString + workdays.get(i);
            }else{
                sortedString = sortedString + workdays.get(i) + "|";
            }

        }*/
        String format = businessid + " " + workday + " " + starttime + " " + endtime;

        return format;
    }


}
