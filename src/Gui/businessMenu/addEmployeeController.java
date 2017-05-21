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
    private Label errorName;

    @FXML
    private Label errorTFN;

    @FXML
    private Label errorPho;


    /*
     * Return to business menu, cancel action
     */
    @FXML
    void cancel(ActionEvent event) throws IOException {
        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    /*
     * Adds employee
     */
    @FXML
    void addIsClicked(ActionEvent event) throws IOException{

        //checks validity of employee name
        if(driver.verifyEmployeeName(name.getText())){
            //alert - invalid employee name

        }

        //checks validity of TFN
        else if(driver.verifyEmployeeTFN(tfn.getText())){
            //alert - invalid tfn

        }

        //checks validity of mobile no
        else if(driver.verifyEmployeeMobile(number.getText())){
            //alert - invalid mobile
        }

        //checks that every other employee info is valid
        else if(!driver.verifyEmployeeName(name.getText()) && !driver.verifyEmployeeTFN(tfn.getText()) &&  !driver.verifyEmployeeMobile(number.getText())){
            //if valid, add employee
            filewriter.WriteToEmployee(new Employee(businessID,employeeID.getText(), name.getText(), tfn.getText(), number.getText()), "employeeList.txt");

            //Still need to add employee workday
            pass("businessMenu.fxml", businessID);
            switchToBusinessMenu(event);

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeID.setText(driver.generateEmployeeNo(businessID));
        errorName.setVisible(false);
        errorPho.setVisible(false);
        errorTFN.setVisible(false);

        //Check Name validity
        name.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(listenerNameCheck(newText)){
                    errorName.setVisible(false); //valid name
                }else{
                    errorName.setVisible(true); //invalid name
                }
            }
            else{
                errorName.setVisible(false);
            }
        });

        //CHECK TFN validity

        tfn.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(!listenerTFNCheck(newText)){
                    errorTFN.setVisible(false);
                }else{
                    errorTFN.setVisible(true);
                }
            }
            else{
                errorTFN.setVisible(false);
            }
        });


        //CHECK PHONE validity
        number.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(!listenerPhoCheck(newText)){
                    errorPho.setVisible(false);
                }else{
                    errorPho.setVisible(true);
                }
            }
            else{
                errorPho.setVisible(false);
            }
        });

    }


    /*
     * check validity of employee name
     */
    private boolean listenerNameCheck(String name){

        //checks length validity
        if((name.length()< 3)||(name.length()>20)){

            return false;  //invalid
        }

        //checks format validity
        return name.matches("^[a-zA-Z\\s]+$");
    }

    /*
     * checks for TFN validity
     */
    private boolean listenerTFNCheck(String tfn){
        int length = tfn.length();

        //checks length validity
        if(length < 8 || length>9){


            return true; //invalid
        }

        //checks format validity - all digits required
        return !isNumeric(tfn);
    }

    /*
     * checks validity of phone number entered
     */
    private boolean listenerPhoCheck(String phone){

        //phone length validity
        if(phone.length() != 10){

            return true; //invalid length
        }

        //format validity
        if(!isNumeric(phone)) {


            return true; //invalid - non numeric
        }

        //mobile nomust start with 04
        return phone.charAt(0) != '0' || phone.charAt(1) != '4';
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    /*
     * return to business menu
     */
    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    /*
     * function to store business ID
     */
    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        businessMenuController controller = loader.getController();

        //function in the controller u go must contain this
        businessMenuController.setBusinessID(parameterToPass);

    }
}