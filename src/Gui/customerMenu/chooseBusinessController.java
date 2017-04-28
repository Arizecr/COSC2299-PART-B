package Gui.customerMenu;

import coreFunctions.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.Login;

import java.io.IOException;

/**
 * Created by yesmi on 28/04/2017.
 */
public class chooseBusinessController {
    @FXML
    private Label employeeID;


    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;
    public static String customerID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    public String  getBusinessID(){
        return businessID;

    }
    public static void setCustomerID(String cid){
        customerID = cid;

    }

    public String  getCustomerID(){
        return customerID;

    }
    private void pass(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        customerMenuController controller = loader.getController();
        controller.setBusinessID(businessID);
        controller.setCustomerID(customerID);

    }
    @FXML //exit system
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    //go back to business menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
 /*   @Override
    public void initialize(URL location, ResourceBundle resources) {
        getBusinessID();getCustomerID();
    }*/



}
