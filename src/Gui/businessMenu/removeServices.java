package Gui.businessMenu;

/**
 * Created by asus on 30-Apr-17.
 */

import Gui.Controller;
import bookings.Services;
import coreFunctions.Driver;
import coreFunctions.WriteToFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class removeServices extends Controller implements Initializable{
    Services service = new Services();
    WriteToFile filewriter = new WriteToFile();

    public static String businessID;

    public static void setBusinessID(String busid){
        businessID = busid;
    }


    @FXML
    private TextField name;

    @FXML
    private TextField length;

    @FXML
    private TextField cost;

    @FXML
    private Label serviceID;



    @FXML
    void addIsClicked(ActionEvent event) throws IOException{
        if(service.checkName(name.getText())){
            //alert

        }
        else if(service.checkDur(length.getText())){
            //alert

        }
        else if(service.checkCost(cost.getText())){
            //alert
        }
        else if(!service.checkName(name.getText()) && !service.checkDur(length.getText()) &&  !service.checkCost(cost.getText())){
            filewriter.WriteToWorkingdayTXT((new Services(businessID,serviceID.getText(), name.getText(), length.getText(), cost.getText())).toString(), "services.txt");

            pass("businessMenu.fxml", businessID);
            switchToBusinessMenu(event);

        }
    }


    @FXML
    void cancel(ActionEvent event) throws IOException {
        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        serviceID.setText(service.generateServiceNo());
    }

    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        businessMenuController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setBusinessID(parameterToPass);

    }

}
