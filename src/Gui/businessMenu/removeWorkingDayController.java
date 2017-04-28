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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class removeWorkingDayController implements Initializable{
    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    @FXML
    private ListView<String> workerList;

    Driver driver = new Driver();


    @FXML
    private TextField eid;

    @FXML
    private TextField day;

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

    @FXML
    void removeWorkday(ActionEvent event) {
        driver.deleteEmployeeWorktimes(businessID, eid.getText(), day.getText());
        ArrayList<String> array = driver.loadInfo();
        workerList.setItems(FXCollections.observableArrayList(array));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> array = driver.loadInfo();
        if(array!= null){
            workerList.setItems(FXCollections.observableArrayList(array));

            eid.textProperty().addListener((obs, oldText, newText) -> {
                ArrayList<String> array2 = new ArrayList<>();
                if(!(eid.getText() == null)){
                    ArrayList<String> arrayz = driver.loadInfo();
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
}
