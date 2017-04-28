package Gui.businessMenu;

import EmployeeAvailabilityDays.AvailableDay;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class showWorkerAvailibilityController implements Initializable{
    AvailableDay ad = new AvailableDay();



    @FXML
    private ListView<String> workerList;

    @FXML
    private TextField name;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<String> array = ad.loadInfo();
        workerList.setItems(FXCollections.observableArrayList(array));

        name.textProperty().addListener((obs, oldText, newText) -> {
            ArrayList<String> array2 = new ArrayList<>();
            if(!(name.getText() == null)){
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
