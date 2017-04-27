package Gui;

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
                switchToHomepage(event);
                busId = username.getText();
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
                busId = username.getText();
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
    void startBooking(ActionEvent event) throws IOException {



    }

    @FXML
    void startRegister(ActionEvent event) throws IOException {
        switchToRegister(event);

    }


    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu/businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    private void switchToHomepage(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("homepage.fxml"));
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
