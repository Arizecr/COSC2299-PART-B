package Gui.businessMenu;

import Gui.Controller;
import coreFunctions.Driver;
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

    //set business id
    public static void setBusinessID(String bid){
        businessID = bid;

    }

    //get business id
    public String  getBusinessID(){
        return businessID;

    }

    //pass business id throughout functions/scenes
    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        addEmployeeController controller = loader.getController();
        addEmployeeController.setBusinessID(parameterToPass);

    }

    //go to show worker availibility scene
    private void passToShowWorkerAvailibility(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        showWorkerAvailibilityController controller = loader.getController();
        showWorkerAvailibilityController.setBusinessID(parameterToPass);

    }

    //go to remove working day scene
    private void passToRemoveWorkingDay(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        removeWorkingDayController controller = loader.getController();
        removeWorkingDayController.setBusinessID(parameterToPass);

    }

    //go to add working day scene
    private void passToAddWorkingDay(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        addWorkingDayController controller = loader.getController();
        addWorkingDayController.setBusinessID(parameterToPass);

    }

    //go to view business hours scene
    private void passToViewBusinessHours(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        viewBusinessHours controller = loader.getController();
        viewBusinessHours.setBusinessID(parameterToPass);

    }

    //go to view availible days scene (part of show worker availibility scene)
    private void passToAvailableDay(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        addEmployeeAvailableDayController controller = loader.getController();
        addEmployeeAvailableDayController.setBusinessID(parameterToPass);

    }

    //go to choose customer scene (part of make booking function)
    private void passToChooseCustomer(String fxmlFile, String parameterToPass) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        chooseCustomerController controller = loader.getController();
        chooseCustomerController.setBusinessID(parameterToPass);

    }

    //go to add services scene
    private void passToAddServices(String fxmlFile, String parameterToPass) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        addServices controller = loader.getController();
        addServices.setBusinessID(parameterToPass);

    }

    //go to view booking summary scene
    private void passToViewBookingSummary(String fxmlFile, String parameterToPass) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        viewBookingSummaryController controller = loader.getController();
        viewBookingSummaryController.setBusinessID(parameterToPass);

    }




    @FXML
    public void initializing(ActionEvent event) {
        System.out.println(businessID);
        //employeeID.setText(driver.generateEmployeeNo());
    }


    //go to add employee scene
    @FXML
    private void switchToAddEmployee(ActionEvent event) throws IOException {



        pass("addEmployee.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);


        //System.out.println(busId); //get Businessid
    }

    //go to add worker availibility scene
    @FXML
    void switchToAddAvailibility(ActionEvent event) throws IOException {

        passToAvailableDay("addEmployeeAvailableDay.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("addEmployeeAvailableDay.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
    }

    /*
     * return to login
     */
    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("../login.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //go to add working day scene
    @FXML
    private void switchToAddWorkingDay(ActionEvent event) throws IOException {

        passToAddWorkingDay("addWorkingDay.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("addWorkingDay.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //go to show worker availibility scene
    @FXML
    private void switchToShowWorkerAvailibility(ActionEvent event) throws IOException {
        passToShowWorkerAvailibility("showWorkerAvailibility.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("showWorkerAvailibility.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //go to remove working day scene
    @FXML
    private void removeWorkingDay(ActionEvent event) throws IOException {
        passToRemoveWorkingDay("removeWorkingDay.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("removeWorkingDay.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //go to adjust business hours scene
    @FXML
    private void switchToAdjustBusinessHours(ActionEvent event) throws IOException {
        passToViewBusinessHours("viewBusinessHours.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBusinessHours.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML //add services
    private void switchToAddServices(ActionEvent event) throws IOException {
        System.out.println(businessID);
        passToAddServices("addServices.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("addServices.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML //view summaries of booking
    private void switchToViewBookingSummary(ActionEvent event) throws IOException {
        passToViewBookingSummary("viewBookingSummary.fxml",businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBookingSummary.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML //view summaries of booking
    private void switchToMakeBooking(ActionEvent event) throws IOException {
        passToChooseCustomer("chooseCustomer.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("chooseCustomer.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseCustomer.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
        chooseCustomerController controller = loader.getController();
        //function in the controller u go must contain this
        controller.startChoose(app_stage);



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
