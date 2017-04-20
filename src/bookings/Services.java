package bookings;

import coreFunctions.WriteToFile;
import test.Logging;

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
    private String lengthT;
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    Logging l =new Logging();
    public static ArrayList<Services> serviceList = new ArrayList<>();
    public Services(String bId,String sId,String name,String lengthT) {
        this.bId = bId;
        this.sId = sId;
        this.name = name;
        this.lengthT = lengthT;
    }
    public Services() {

    }
    public void printService(String b) {
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("services.txt"));

            try {
                String x;
                while ((x = br.readLine()) != null) {
                    // printing out each line in the file
                    String Details[] = x.split(":", 4);
                    String bid = Details[0];
                    String sid = Details[1];
                    String n = Details[2];
                    String l = Details[3];
                    Services addS = new Services(bid,sid,n,l);
                    serviceList.add(addS);
                    if(bid.equals(b)){System.out.println(sid + " | " + n + " | " + l);}

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


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }
    public void addService(String b){//,String sId,String name, String time
       String s = generateServiceNo(); //generate user id
        int sSize = serviceList.size();
        WriteToFile w = new WriteToFile();
        String n = null;
        String len = null;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Name of service: ");
            n = reader.nextLine();
            System.out.print("length in HH:mm ");
            len = reader.nextLine();
        }while(!checkService(n,len));
        Services addS = new Services(b,s,n,len);
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
        System.out.print("Service added");

        //add in code to add employees to the service
    }
    public void rewriteToFile( ArrayList serviceList,String filename){
        WriteToFile w = new WriteToFile();
        if(serviceList.size()>=0){w.reWriteToWorkingdayTXT(serviceList.get(0).toString(), filename);}
        for(int i=1; i < serviceList.size() ;i++){
            w.WriteToWorkingdayTXT(serviceList.get(i).toString(), filename);
        }
    }
    public int checkID(String n){
        for(int i=0; i < serviceList.size() ;i++){
            if(n.equals(serviceList.get(i).sId)){
              return i+1;
            }
        }

        return 0;
    }
    public boolean checkService(String n, String l){
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

        return bId + ":" + sId + ":" + name + ":" + lengthT;
    }

}
