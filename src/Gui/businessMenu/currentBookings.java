package Gui.businessMenu;

/**
 * Created by asus on 28-Apr-17.
 */

import Gui.Controller;
import coreFunctions.Driver;
import EmployeeAvailabilityDays.AvailableDay;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class currentBookings implements Initializable {
    Driver d = new Driver();

    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }



    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        controller.setBusinessID(businessID);

    }

    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        viewBookingSummaryController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setBusinessID(parameterToPass);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<String> array = d.loadInfo();
        printFile();


    }

    public void printFile(){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String name ="";
        String time ="";
        String service="";
        String cID ="";
        try {
            br = new BufferedReader(new FileReader("currentBookings.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",6);
                    bId = Details[0];
                    day = Details[1];
                    name = Details[2];
                    time = Details[3];
                    service = Details[4];
                    cID = Details[5];
                    day = day.substring(0,1).toUpperCase() + day.substring(1);

                }
                //prints error
            } catch (IOException e) {

            }
            catch (ArrayIndexOutOfBoundsException ae) {

            }
            //file cannot be found
        } catch (FileNotFoundException e) {
        }
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        //Passes to addEmployeeController

        passToViewBookingSummary("viewBookingSummaryController.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBookingSummaryController.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);


        //System.out.println(busId); //get Businessid

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        passToBusinessMenu("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    private void passToViewBookingSummary(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        viewBookingSummaryController controller = loader.getController();
        controller.setBusinessID(parameterToPass);

    }


}
