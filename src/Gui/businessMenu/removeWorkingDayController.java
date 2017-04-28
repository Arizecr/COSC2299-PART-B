package Gui.businessMenu;

import coreFunctions.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class removeWorkingDayController {
    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    Driver driver = new Driver();


    @FXML
    private TextField eid;

    @FXML
    private TextField day;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void removeWorkday(ActionEvent event) {
        driver.deleteEmployeeWorktimes(businessID, eid.getText(), day.getText());


    }

}
