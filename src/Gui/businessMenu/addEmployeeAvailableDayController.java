package Gui.businessMenu;

import BusinessWorkDays.Workday;
import EmployeeAvailabilityDays.AvailableDay;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.BusinessMenu;
import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addEmployeeAvailableDayController implements Initializable{
    ArrayList<ArrayList<String>> employee = new ArrayList<ArrayList<String>>();
    public static ArrayList<Workday> workhours = new ArrayList<>();
    public static ArrayList<String> availability = new ArrayList<>();
    ArrayList<String> clarityArrayAD = new ArrayList<>();
    Workday w = new Workday();
    BusinessMenu b = new BusinessMenu();
    AvailableDay ad = new AvailableDay();
    Employee emp = new Employee();

    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    @FXML
    private TextField eid;

    @FXML
    private TextField day;

    @FXML
    private Label eidError;

    @FXML
    private Label dayError;

    @FXML
    private Label endError;

    @FXML
    private Label startError;


    @FXML
    private ListView<String> workerList;


    @FXML
    private TextField starttime;

    @FXML
    private TextField endtime;


    /*
     * GUI function to add employee availbility
     */
    @FXML
    void addAvailibility(ActionEvent event) {
        ad.loadInfo(businessID);

        //check if employee id exirts
        if(!emp.checkEmployeeID(businessID,eid.getText())){
            //alert if invalid

        }

        //check if start time is valid
        else if(b.checktime(starttime.getText())){
            //alert if invalid

        }

        //check if end time is valid
        else if(b.checktime(endtime.getText())){
            //alert if invalid

        }

        //check if day is valid
        else if(b.checkD(day.getText())){
            //alert if invalid

        }

        //load in information pertaining to valid work days
        w.Details();

        //check validity of availibility (day/time) entered
        if(!w.readWork(businessID,day.getText().toLowerCase(),starttime.getText(),endtime.getText()) && ad.checkDay(businessID,eid.getText(),day.getText())){

            ad.addEmployeeAvailability(businessID, eid.getText(), day.getText(), starttime.getText(), endtime.getText());
            ArrayList<String> array2list = AvailableDay.Bavailability;
            clarityArrAD(array2list,null);
            workerList.setItems(FXCollections.observableArrayList(clarityArrayAD));

        }

        //checks validity of assigning the availible day/time to employee
        if(emp.checkEmployeeID(businessID,eid.getText())){
            if(ad.checkFirstTimeEmployee(eid.getText(),businessID)){
                if(!w.readWork(businessID,day.getText().toLowerCase(),starttime.getText(),endtime.getText())){
                    System.out.println("Check1");
                    ad.addEmployeeAvailability(businessID, eid.getText(), day.getText(), starttime.getText(), endtime.getText());
                    ArrayList<String> array2list = AvailableDay.Bavailability;
                    clarityArrAD(array2list,null);
                    workerList.setItems(FXCollections.observableArrayList(clarityArrayAD));
                }
            }
        }


    }

    private ArrayList clarityArrAD(ArrayList<String> array, String e){
        clarityArrayAD.clear();
        for(int i=0; i<array.size(); i++){
            String arrayinfo[] = array.get(i).split(" ",5);
            String bID = arrayinfo[0];
            String eID = arrayinfo[1];
            if(e!=null && e.equals(eID)) {
                String name = emp.getEmployeeName(bID, eID);

                clarityArrayAD.add(name + "(" + arrayinfo[1] + ")" + " " + arrayinfo[2] + " Start: " + arrayinfo[3] + " End: " + arrayinfo[4]);
            }
            else if(e==null ) {
                String name = emp.getEmployeeName(bID, eID);

                clarityArrayAD.add(name + "(" + arrayinfo[1] + ")" + " " + arrayinfo[2] + " Start: " + arrayinfo[3] + " End: " + arrayinfo[4]);
            }
        }




        return clarityArrayAD;
    }


    /*
     * checks if employee ID is valid
     */
    private boolean checkEID(){

        for(int i=0; i<employee.size(); i++){
            if(employee.get(i).get(0).equals(eid.getText())){
                System.out.println(employee.get(i).get(0));
                return false;
            }
        }

        return true;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        //Passes to addEmployeeController

        passToBusinessMenu("businessMenu.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);


        //System.out.println(busId); //get Businessid

    }

    /*
     * go back to business menu, and pass business id
     */
    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        businessMenuController.setBusinessID(businessID);

    }

    /*
     * load employee information
     */
    private ArrayList readEmployee(){


        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    String loginDetails[] = x.split(" ",5);
                    if(loginDetails[0].equals(businessID)){
                        ArrayList<String> test = new ArrayList<>();
                        test.add(loginDetails[1]);
                        test.add(loginDetails[2]);
                        employee.add(test);
                    }


                }

                //prints error
            } catch (IOException e) {
                // e.printStackTrace();
            }

            //file cannot be found
        } catch (FileNotFoundException e) {

        }

        return employee;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readEmployee();
        ad.loadInfo(businessID);
        ArrayList<String> array = AvailableDay.Bavailability;
        clarityArrAD(array,null);

        eidError.setVisible(false);
        dayError.setVisible(false);
        endError.setVisible(false);
        startError.setVisible(false);


        workerList.setItems(FXCollections.observableArrayList(clarityArrayAD));

        eid.textProperty().addListener((obs, oldText, newText) -> {
            ArrayList<String> array2 = new ArrayList<>();
            ad.loadInfo(businessID);
            ArrayList<String> arrayz = AvailableDay.Bavailability;
            if(!(eid.getText() == null)){
                clarityArrAD(arrayz,eid.getText());
           /*     for(int i=0; i<clarityArrayAD.size(); i++){
                    if(clarityArrayAD.get(i).toLowerCase().contains(newText.toLowerCase())){
                        array2.add(clarityArrayAD.get(i));

                    }
                }*/

            }
            else{

                clarityArrAD(arrayz,null);
            }
            workerList.setItems(FXCollections.observableArrayList(clarityArrayAD));
        });


//CHECK FOR Employee ID
        eid.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(emp.checkEmployeeID(businessID,newText)){
                    eidError.setVisible(false);
                }else{
                    eidError.setVisible(true);
                }
            }
            else{
                eidError.setVisible(false);
            }
        });

//CHECK FOR DAY
        day.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(listenerCheckDay(newText)){
                    dayError.setVisible(false);
                }else{
                    dayError.setVisible(true);
                }
            }
            else{
                dayError.setVisible(false);
            }
        });

        //CHECK TIME
        starttime.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(checkTime(newText)){
                    startError.setVisible(false);
                }else{
                    startError.setVisible(true);
                }
            }
            else{
                startError.setVisible(false);
            }
        });

        endtime.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(checkTime(newText)){
                    endError.setVisible(false);
                }else{
                    endError.setVisible(true);
                }
            }
            else{
                endError.setVisible(false);
            }
        });



    }





    private boolean listenerEIDCheck(String empeid){

        for(int i=0; i<employee.size(); i++){
            if(employee.get(i).get(0).equals(empeid)){
                return true;
            }
        }

        return false;
    }

    private boolean listenerCheckDay(String day){
        try{
            DateFormat time = new SimpleDateFormat("EEEE");
            time.parse(day);
        }
        catch(ParseException e){
            return false;
        }
        return true;
    }

    private boolean checkTime(String t){

        try{
            DateFormat time = new SimpleDateFormat("HH:mm");
            time.parse(t);
        }
        catch(ParseException e){
            // System.out.println("Invalid time:");
            return false;
        }
        return !(!t.contains(":00") && !t.contains(":30"));

    }


}
