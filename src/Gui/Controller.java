package Gui;

import Gui.businessMenu.businessMenuController;
import Gui.customerMenu.chooseBusinessController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.Login;
import menu.Register;
import user.Business;
import user.Customer;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    Login loginMenu = new Login();
    Register registerMenu = new Register();
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static ArrayList<Business> businessList = new ArrayList<>();


    @FXML
    private Button login;

    @FXML
    public Button closeButton;

    @FXML
    private Button register;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public static String busId; //stores businessId


    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        businessMenuController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setBusinessID(parameterToPass);

    }
    private void passC(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        chooseBusinessController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setCustomerID(parameterToPass);

    }




    @FXML
    void startLogin(ActionEvent event) throws IOException {

        loginMenu.loadCustomerInformation();
        loginMenu.loadOwnerInformation();
        if(username.getText().length() <1 || password.getText().length()<1){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username/password combination. Try again.");

            alert.showAndWait();

        }
        else if(username.getText().charAt(0) == 'c'){
            if(loginMenu.getVerification("customer",username.getText(),password.getText())){
                busId = username.getText();//is the customer
                passC("customerMenu/chooseCustomer.fxml", busId);
                switchToChooseBusiness(event);
            }
            else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username/password combination. Try again.");


                alert.showAndWait();
            }
        }
        else if(username.getText().charAt(0) == 'b'){
            if(loginMenu.getVerification("owner",username.getText(),password.getText())){
                // passes parameter to business menu controller
                busId = username.getText();
                pass("businessMenu/businessMenu.fxml", busId);
                switchToBusinessMenu(event);
            }
            else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username/password combination. Try again.");

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username/password combination. Try again.");

            alert.showAndWait();
        }



    }


    @FXML
    void startRegister(ActionEvent event) throws IOException {
        switchToRegister(event);

    }
    private void switchToChooseBusiness(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("customerMenu/chooseBusiness.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerMenu/chooseBusiness.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
        chooseBusinessController controller = loader.getController();
        //function in the controller u go must contain this
        controller.startChoose(app_stage);


    }

    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu/businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }



    private void switchToRegister(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }






}
