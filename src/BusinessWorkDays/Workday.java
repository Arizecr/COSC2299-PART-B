package BusinessWorkDays;

import coreFunctions.WriteToFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Martin on 28/03/2017.
 */
public class Workday

{
    int tmp;
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



    private ArrayList<String> workdays;   //0-6 = Monday-Sunday
    private String starttime;
    private String endtime;
    private String businessid;


    public Workday(String businessid, ArrayList<String> workdays, String starttime, String endtime){
        this.businessid = businessid;
        this.workdays = workdays;
        this.starttime = starttime;
        this.endtime = endtime;

    }

    public void sort(){
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
    }

    public void writeToFile(){
        write.WriteToWorkingdayTXT(toString(), "workdaysList.txt");

    }



    public String toString(){
        sort();

        for(int i=0; i< workdays.size(); i++){
            if(i == workdays.size()-1){
                sortedString = sortedString + workdays.get(i);
            }else{
                sortedString = sortedString + workdays.get(i) + "|";
            }

        }
        String format = businessid + " " + sortedString + " " + starttime + "-" + endtime;

        return format;
    }


}
