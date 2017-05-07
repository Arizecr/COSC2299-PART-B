package Gui.businessMenu;

/**
 * Created by asus on 28-Apr-17.
 */

import Gui.Controller;
import coreFunctions.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewBookingSummaryController extends Controller implements Initializable{
    Driver d = new Driver();

    public static String businessID;

    /*
     * set current business id
     */
    public static void setBusinessID(String bid){
        businessID = bid;

    }

    /*
     * go back to main menu
     */
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

    //go back to business menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


    //go to business menu
    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        controller.setBusinessID(businessID); //set business id

    }

    //go to show current bookings
    private void passToShowCurrentBooking(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        currentBookings controller = loader.getController();
        controller.setBusinessID(parameterToPass); //pass in business id

    }

    //go to show past bookings
    private void passToShowPastBooking(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        pastBookings controller = loader.getController();
        controller.setBusinessID(parameterToPass); //pass in business id

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> array = d.loadInfo();
    }



    //switch scene to past bookings
    @FXML
    private void switchToPastBookings(ActionEvent event) throws IOException {
        passToShowPastBooking("pastBookings.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("pastBookings.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pastBookings.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
       pastBookings controller = loader.getController();
        //function in the controller u go must contain this
        controller.startViewBook(app_stage);
    }

    //switch to current bookings
    @FXML
    private void switchToCurrentBookings(ActionEvent event) throws IOException {
        passToShowCurrentBooking("currentBookings.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("currentBookings.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("currentBookings.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
       currentBookings controller = loader.getController();
        //function in the controller u go must contain this
        controller.startViewBook(app_stage);
    }



}


