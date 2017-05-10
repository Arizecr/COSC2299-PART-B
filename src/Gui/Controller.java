package Gui;

import Gui.businessMenu.businessMenuController;
import Gui.customerMenu.customerMenuController;
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
    private void passC(String fxmlFile, String parameterToPass,String businessId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        customerMenuController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setCustomerID(parameterToPass);
        //function in the controller u go must contain this
        controller.setBusinessID(businessId);

    }



    /*
     * Display login menu
     */
    @FXML
    void startLogin(ActionEvent event) throws IOException {

        loginMenu.loadCustomerInformation(); //load customer info
        loginMenu.loadOwnerInformation(); //load owner info

        //checks validity via length
        if(username.getText().length() <1 || password.getText().length()<1){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username/password combination. Try again.");

            alert.showAndWait();

        }

        //customer account- check validity
        else if(username.getText().charAt(0) == 'c'){
            loginMenu.loadCustomerInformation();
            //check if password/username combination exists in the system
            if(loginMenu.getVerification("customer",username.getText(),password.getText())){
                busId = username.getText();//is the customer
                String b = null;
                for(int i=0; i < loginMenu.customerList.size() ;i++){

                    if(username.getText().equals(loginMenu.customerList.get(i).getUsername())){
                       b = loginMenu.customerList.get(i).getBusiness();

                    }

                }

                passC("customerMenu/customerMenu.fxml", busId,b);
                switchToC(event); //valid
            }
            else{ //invalid
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username/password combination. Try again.");


                alert.showAndWait();
            }
        }

        //business owner account - check validity
        else if(username.getText().charAt(0) == 'b'){
            if(loginMenu.getVerification("owner",username.getText(),password.getText())){
                // passes parameter to business menu controller
                busId = username.getText();
                pass("businessMenu/businessMenu.fxml", busId);
                switchToBusinessMenu(event);
            }
            else { //invalid
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username/password combination. Try again.");

                alert.showAndWait();
            }
        }
        else { //invalid, username does not start with 'c' or 'b'
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

    @FXML
    void startbRegister(ActionEvent event) throws IOException {
        switchToBRegister(event);

    }
    private void switchToC(ActionEvent event) throws IOException {;
        Parent home_page = FXMLLoader.load(getClass().getResource("customerMenu/customerMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();


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
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
        registerController controller = loader.getController();
        //function in the controller u go must contain this
        controller.startChoose(app_stage);
    }


    private void switchToBRegister(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("bregister.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }



}
