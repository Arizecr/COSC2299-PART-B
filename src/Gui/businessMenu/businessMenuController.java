package Gui.businessMenu;

import Gui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.Login;
import coreFunctions.Driver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by xemorth on 26/04/2017.
 */

public class businessMenuController extends Controller implements Initializable{

    @FXML
    private Label employeeID;


    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    public String  getBusinessID(){
        return businessID;

    }


    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        addEmployeeController controller = loader.getController();
        controller.setBusinessID(businessID);

    }




    @FXML
    public void initializing(ActionEvent event) {
        System.out.println(businessID);
        //employeeID.setText(driver.generateEmployeeNo());
    }



    @FXML
    private void switchToAddEmployee(ActionEvent event) throws IOException {
        System.out.println(getBusinessID());
        //Passes to addEmployeeController

        pass("addEmployee.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);


        //System.out.println(busId); //get Businessid
    }

    @FXML
    private void switchToAddWorkingDay(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("addWorkingDay.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void switchToShowWorkerAvailibility(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("showWorkerAvailibility.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void removeWorkingDay(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("removeWorkingDay.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void switchToAdjustBusinessHours(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBusinessHours.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML //add services
    private void switchToAddServices(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("addServices.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML //add services
    private void switchToViewBookingSummary(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBookingSummary.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML //exit system
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    //go back to business menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getBusinessID();
    }
}
