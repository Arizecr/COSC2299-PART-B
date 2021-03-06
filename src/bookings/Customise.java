package bookings;

import coreFunctions.WriteToFile;
import test.Logging;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yesmi on 20/05/2017.
 */
public class Customise {
    public static ArrayList<Customise> customiseList = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    WriteToFile w = new WriteToFile();
    Logging l =new Logging();
    private String bId;
    private String menuName;
    private String booking;
    private String viewing;
    private String colour;
    public Customise(){}
    public Customise(String bId, String menuName, String booking, String viewing,String colour) {
        this.menuName = menuName;
        this.bId = bId;
        this.booking = booking;
        this.viewing = viewing;
        this.colour=colour;

    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public void setViewing(String viewing) {
        this.viewing = viewing;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getbId() {
        return bId;
    }

    public String getBooking() {
        return booking;
    }

    public String getViewing() {
        return viewing;
    }

    public String getColour(){return colour;}

    public void loadCustom() {
        BufferedReader br;
        customiseList = new ArrayList<>();
        try {


            br = new BufferedReader(new FileReader("customize.txt"));

            try {

                String x;
                String line = null;

                while ((x = br.readLine()) != null) {

                    // printing out each line in the file
                    String Details[] = x.split(":", 5);
                    for(int i=1;i<4;i++){
                        if(Details[i].equals("null")){
                            Details[i]=null;
                        }
                    }
                    String bid = Details[0];
                    String t1 = Details[1];
                    String t2 = Details[2];
                    String t3 = Details[3];
                    String c = Details[4];

                  /*  String empID[] = e.split(",");
                    for(String emp:empID){
                        EOserviceList.add(emp);
                    }*/
                    Customise addC = new Customise(bid,t1,t2,t3,c);

                    customiseList.add(addC);

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
    public void addCustom(String b,Customise c){
        boolean isNotFound = true;
        loadCustom();
        for(int i=0; i < customiseList.size() ;i++){
            if(b.equals(customiseList.get(i).getbId())){
               // System.out.println("res");
                customiseList.set(i,c);
                isNotFound = false;

            }
        }
        //there is no customisation for this business yet
        if(isNotFound) {
            customiseList.add(c);
        }
        w.rewriteToFile(customiseList, "customize.txt");
    }

    public Customise getCustom(String b){
        loadCustom();
        for(int i=0; i < customiseList.size() ;i++){
            if(b.equals(customiseList.get(i).getbId())){

                return customiseList.get(i);
            }
        }
        return null;

    }
    public String toString(){
        return bId + ":"+ menuName+ ":"+ booking+ ":"+viewing+ ":"+colour;
    }
}
