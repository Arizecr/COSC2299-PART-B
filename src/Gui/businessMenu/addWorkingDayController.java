package Gui.businessMenu;

import EmployeeAvailabilityDays.AvailableDay;
import coreFunctions.Driver;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.BusinessMenu;
import user.Employee;

import java.io.IOException;
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
    private ListView<String> workday2list;

    @FXML
    private TextField start;

    @FXML
    private TextField end;

    @FXML
    private TextField day;

    @FXML
    private TextField eid;



    @FXML
    void back(ActionEvent event) throws IOException {
        //Passes to addEmployeeController

        passToBusinessMenu("businessMenu.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);


        //System.out.println(busId); //get Businessid

    }



    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        controller.setBusinessID(businessID);

    }

    /********************************************************


     KINDA NOT WORKING


     *********************************************************/


    @FXML
    void addShift(ActionEvent event) {
        Employee emp = new Employee();
        if(b.checktime(start.getText())){
            //alert incorrect time
        }
        else if(b.checktime(end.getText())){
            //alert
        }
        else if(!emp.checkEmployeeID(businessID,eid.getText()))
        {//alert incorrect employee
        }
        else if(b.checkD(day.getText())){}
        else if (!b.Worktimes(businessID, eid.getText(),day.getText(),start.getText(),end.getText())){
            driver.addWorkdays(businessID,eid.getText(),day.getText(),start.getText(),end.getText());

            // Dont worry about these
            ArrayList<String> array = ad.loadInfo();
            workerList.setItems(FXCollections.observableArrayList(array));
            ArrayList<String> array2list = driver.loadInfo();
            workday2list.setItems(FXCollections.observableArrayList(array2list));
        }
    }









    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> array = ad.loadInfo();
        workerList.setItems(FXCollections.observableArrayList(array));

        ArrayList<String> array2list = driver.loadInfo();
        workday2list.setItems(FXCollections.observableArrayList(array2list));

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

        eid.textProperty().addListener((obs, oldText, newText) -> {
            ArrayList<String> array3 = new ArrayList<>();
            if(!(eid.getText() == null)){
                AvailableDay ad = new AvailableDay();
                ArrayList<String> arrayzz = driver.loadInfo();
                for(int i=0; i<arrayzz.size(); i++){
                    if(arrayzz.get(i).toLowerCase().contains(newText.toLowerCase())){
                        array3.add(arrayzz.get(i));

                    }
                }
                workday2list.setItems(FXCollections.observableArrayList(array3));
            }

        });


    }


}
