package Gui;

import coreFunctions.WriteToFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import menu.Login;
import menu.Register;
import user.Business;
import user.Customer;

import java.io.IOException;
import java.util.ArrayList;

public class registerController {
    Register registerMenu = new Register();
    WriteToFile toTxt = new WriteToFile();
    Login login = new Login();
    public static Business selectedB = new Business(null,null,null,null,null,null);
    public static Business not = new Business(null,null,null,null,null,null);
    private Stage primaryStage;

    @FXML
    private TextField username;
    @FXML
    private ChoiceBox business;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField p;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField mobile;

    @FXML
    private Button registerAccount;


    public void startChoose(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        ArrayList<String> bus = new ArrayList<>();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("register.fxml"));

        login.loadOwnerInformation();
        //ArrayList<Button> b = new ArrayList<>();
        AnchorPane root = new AnchorPane();

        //gets all the names of all business's registered to the system
        for(int i=0;i<login.businessList.size();i++){

            String n = login.businessList.get(i).getName();
            bus.add(n);

        }
        business.setItems(FXCollections.observableArrayList(bus));
        business.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //this service is saved to the class for use in adding end time of service
                selectedB = login.businessList.get(newValue.intValue());

            }
        });
        business.setTooltip(new Tooltip("Select the Business"));
        ((AnchorPane) rootNode).getChildren().add(business);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }

    /*
     * Create a new account, customer/business
     */
    @FXML
    void createAccount(ActionEvent event) throws IOException {

        //checks validity of account information
        if(selectedB.toString().equals(not.toString())){//checks service selected
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select Business.");
            alert.showAndWait();
        }
        else if(registerMenu.testUser(username.getText())){
            int valid = registerMenu.testReg(password.getText(),name.getText(),address.getText(),mobile.getText());

            if(valid == 0&&p.getText().equals(password.getText())){
                toTxt.WriteToTXT(new Customer(selectedB.getUsername(),username.getText(), password.getText(), name.getText(), address.getText(), mobile.getText()), "customerinfo.txt");
                switchToLogin(event); //valid
            }

            //passwords do not match
            else if(!p.getText().equals(password.getText())){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Password does not match. Try again.");

                alert.showAndWait();
            }

        }else{ //username is invalid, does not start with 'c' or 'b'
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username entered is invalid. Try again.");

            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


    /*
     * return to login
     */
    private void switchToLogin(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
