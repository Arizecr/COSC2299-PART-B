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

    public static void setBusinessID(String bid){
        businessID = bid;

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

    //go back to business menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        controller.setBusinessID(businessID);

    }

    private void passToShowCurrentBooking(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        currentBookings controller = loader.getController();
        controller.setBusinessID(parameterToPass);

    }

    private void passToShowPastBooking(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        pastBookings controller = loader.getController();
        controller.setBusinessID(parameterToPass);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> array = d.loadInfo();
    }




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
        currentBookings controller = loader.getController();
        //function in the controller u go must contain this
        controller.startViewBook(app_stage);
    }

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


