package Gui.businessMenu;

import BusinessWorkDays.Workday;
import EmployeeAvailabilityDays.AvailableDay;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.BusinessMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addEmployeeAvailableDayController implements Initializable{
    ArrayList<ArrayList<String>> employee = new ArrayList<ArrayList<String>>();
    public static ArrayList<Workday> workhours = new ArrayList<>();
    public static ArrayList<String> availability = new ArrayList<>();
    Workday w = new Workday();
    BusinessMenu b = new BusinessMenu();
    AvailableDay ad = new AvailableDay();

    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    @FXML
    private TextField eid;

    @FXML
    private TextField day;


    @FXML
    private TextField starttime;

    @FXML
    private TextField endtime;

    @FXML
    void addAvailibility(ActionEvent event) {
        ad.loadInfo();
        if(checkEID()){
           //alert

        }
        else if(b.checktime(starttime.getText())){
            //alert

        }
        else if(b.checktime(endtime.getText())){
            //alert

        }
        else if(b.checkD(day.getText())){
            //alert

        }
        w.Details();
        if(!w.readWork(businessID,day.getText().toLowerCase(),starttime.getText(),endtime.getText()) || !ad.checkDay(businessID,eid.getText(),day.getText())){
            ad.addEmployeeAvailability(businessID, eid.getText(), day.getText(), starttime.getText(), endtime.getText());

        }







    }
    private boolean checkEID(){

        for(int i=0; i<employee.size(); i++){
            if(employee.get(i).get(0).equals(eid.getText())){
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

    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        controller.setBusinessID(businessID);

    }


    private ArrayList readEmployee(){


        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    String loginDetails[] = x.split(":",5);
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
    }
}
