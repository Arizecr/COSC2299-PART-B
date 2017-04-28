package Gui.businessMenu;

import EmployeeAvailabilityDays.AvailableDay;
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
import java.util.ResourceBundle;

public class addEmployeeController implements Initializable{
    Driver driver = new Driver();
    AvailableDay av = new AvailableDay();
    WriteToFile filewriter = new WriteToFile();

    public static String businessID;

    public static void setBusinessID(String busid){
        businessID = busid;
    }


    @FXML
    private TextField name;

    @FXML
    private TextField tfn;

    @FXML
    private TextField number;

    @FXML
    private Label employeeID;


    @FXML
    void cancel(ActionEvent event) throws IOException {
        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    @FXML
    void addIsClicked(ActionEvent event) throws IOException{
        if(driver.verifyEmployeeName(name.getText())){
            //alert

        }
        else if(driver.verifyEmployeeTFN(tfn.getText())){
            //alert

        }
        else if(driver.verifyEmployeeMobile(number.getText())){
            //alert
        }
        else if(!driver.verifyEmployeeName(name.getText()) && !driver.verifyEmployeeTFN(tfn.getText()) &&  !driver.verifyEmployeeMobile(number.getText())){
            filewriter.WriteToEmployee(new Employee(businessID,employeeID.getText(), name.getText(), tfn.getText(), number.getText()), "employeeList.txt");

            //Still need to add employee workday
            pass("businessMenu.fxml", businessID);
            switchToBusinessMenu(event);

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeID.setText(driver.generateEmployeeNo(businessID));
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