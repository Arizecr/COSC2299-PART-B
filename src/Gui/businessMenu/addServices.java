package Gui.businessMenu;

/**
 * Created by asus on 30-Apr-17.
 */

import Gui.Controller;
import bookings.Services;
import coreFunctions.WriteToFile;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addServices extends Controller implements Initializable{
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

//    @FXML
//    private Label serviceID;

    @FXML
    private TextField serviceID;

    @FXML
    private ListView<String> serviceList;



    @FXML
    void addIsClicked(ActionEvent event) throws IOException{
        if(!service.checkName(name.getText())){
            //alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Service name entered is invalid. Try again.");

            alert.showAndWait();
        }
        else if(!service.checkDur(length.getText())){
            //alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("length entered is invalid. Try again.");

            alert.showAndWait();

        }
        else if(!service.checkCost(cost.getText())){
            //
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("cost entered is invalid. Try again.");

            alert.showAndWait();
        }
        else if(service.checkName(name.getText()) && service.checkDur(length.getText()) &&  service.checkCost(cost.getText())){
            Services s = new Services(businessID,service.generateServiceNo(businessID), name.getText(), length.getText(), cost.getText());
            filewriter.WriteToWorkingdayTXT(s.toString(), "services.txt");

            pass("businessMenu.fxml", businessID);
            switchToBusinessMenu(event);

        }
    }

    @FXML
    void removeIsClicked(ActionEvent event) throws IOException{

        int index = service.checkID(serviceID.getText());
//        System.out.println("index:");
        service.serviceList.remove(index-1);

        filewriter.rewriteToFile(service.serviceList,"services.txt");
        System.out.print("Service removed");

        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);
    }




    @FXML
    void cancel(ActionEvent event) throws IOException {
        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //ArrayList<Button> b = new ArrayList<>();
        // AnchorPane root = new AnchorPane();

        ArrayList<Services> array = service.serviceList;

        ArrayList<String> array2 = new ArrayList<>();
        array2 = test(array);

        serviceList.setItems(FXCollections.observableArrayList(array2));
    }

    private ArrayList test(ArrayList<Services> array){
        service.printService(businessID);
        ArrayList<String> array2 = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            //makes sure the services of only the current business are displayed

            if(businessID.equals(array.get(i).b())){
                String n = array.get(i).getName() +" - length: ";
                String l = array.get(i).getLengthT();
                String Time[] = l.split("-", 2);
                String hours = Time[0];
                String min = Time[1];
                //final format of string in the list
                n+= hours +" Hours and " +min +" Minutes ($" + array.get(i).getCost()+")";
                array2.add(array.get(i).getsId() + " " + n);


            }

        }

        return array2;
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
