package Gui;

import coreFunctions.WriteToFile;
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
import menu.Register;
import user.Business;
import user.BusinessOwner;

import java.io.IOException;

public class bregisterController {
    Register registerMenu = new Register();
    WriteToFile toTxt = new WriteToFile();
    BusinessOwner bo = new BusinessOwner(null,null,null);
    private Stage primaryStage;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private PasswordField p;

    @FXML
    private TextField name;

    @FXML
    private TextField ownername;

    @FXML
    private TextField address;

    @FXML
    private TextField mobile;

    @FXML
    private Button registerAccount;

    @FXML
    void createAccount(ActionEvent event) throws IOException {

        if(registerMenu.testBUser(username.getText())){
            int valid = registerMenu.testBReg(password.getText(),name.getText(),ownername.getText(),address.getText(),mobile.getText());

            if(valid == 0&&p.getText().equals(password.getText())){


                toTxt.WriteToTXT(new Business(username.getText(), password.getText(), name.getText(),ownername.getText(), address.getText(), mobile.getText()), "businessPending.txt");
                switchToLogin(event);
            }
            else if(!p.getText().equals(password.getText())){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Password does not match. Try again.");

                alert.showAndWait();
            }

        }else{
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


    private void switchToLogin(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
