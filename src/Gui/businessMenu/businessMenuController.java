package Gui.businessMenu;

import Gui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import menu.Login;
import java.io.IOException;


/**
 * Created by xemorth on 26/04/2017.
 */

public class businessMenuController extends Controller{
    Login login = new Login();


    @FXML
    private void switchToAddEmployee(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

        System.out.println(busId); //get Businessid
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
        Parent home_page = FXMLLoader.load(getClass().getResource("adjustBusinessHours.fxml"));
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


}
