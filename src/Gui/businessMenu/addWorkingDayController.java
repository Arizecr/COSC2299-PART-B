package Gui.businessMenu;

import EmployeeAvailabilityDays.AvailableDay;
import coreFunctions.Driver;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import menu.BusinessMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addWorkingDayController implements Initializable{
    BusinessMenu b = new BusinessMenu();
    Driver driver = new Driver();
    AvailableDay ad = new AvailableDay();

    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }
    @FXML
    private ListView<String> workerList;

    @FXML
    private TextField start;

    @FXML
    private TextField end;

    @FXML
    private TextField day;

    @FXML
    private TextField eid;



    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void addShift(ActionEvent event) {
        if(b.checktime(start.getText())){
            //alert
        }
        else if(b.checktime(end.getText())){
            //alert
        }

        if(!b.Worktimes(businessID, eid.getText(),day.getText(),start.getText(),end.getText())){
            driver.addWorkdays(businessID,eid.getText(),day.getText(),start.getText(),end.getText());
            ArrayList<String> array = ad.loadInfo();
            workerList.setItems(FXCollections.observableArrayList(array));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> array = ad.loadInfo();
        workerList.setItems(FXCollections.observableArrayList(array));

        eid.textProperty().addListener((obs, oldText, newText) -> {
            ArrayList<String> array2 = new ArrayList<>();
            if(!(eid.getText() == null)){
                AvailableDay ad = new AvailableDay();
                ArrayList<String> arrayz = ad.loadInfo();
                for(int i=0; i<arrayz.size(); i++){
                    if(arrayz.get(i).toLowerCase().contains(newText.toLowerCase())){
                        array2.add(arrayz.get(i));

                    }
                }
                workerList.setItems(FXCollections.observableArrayList(array2));
            }
        });
    }


}
