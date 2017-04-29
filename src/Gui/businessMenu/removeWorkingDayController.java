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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class removeWorkingDayController implements Initializable{
    public static String businessID;
    ArrayList<String> clarityArrayWD = new ArrayList<>();
    ArrayList<ArrayList<String>> employee = new ArrayList<ArrayList<String>>();

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    @FXML
    private ListView<String> workerList;

    Driver driver = new Driver();

    private ArrayList clarityArrWD(ArrayList<String> array){
        clarityArrayWD.clear();
        for(int i=0; i<array.size(); i++){

            for(int j=0 ; j<employee.size(); j++){

                if(array.get(i).contains(employee.get(j).get(0))){
                    String[] arrayinfo = array.get(i).split(" ", 5);
                    clarityArrayWD.add(employee.get(j).get(1) + "(" + arrayinfo[1] + ")" + " " + arrayinfo[2] + " Start: " + arrayinfo[3] + " End: " + arrayinfo[4]);
                }
            }

        }


        return clarityArrayWD;
    }


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
        clarityArrWD(array);
        workerList.setItems(FXCollections.observableArrayList(clarityArrayWD));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("dwlo");
        readEmployee();
        ArrayList<String> array = driver.loadInfo();
        if(array!= null && employee!=null){
            clarityArrWD(array);
            workerList.setItems(FXCollections.observableArrayList(clarityArrayWD));

            eid.textProperty().addListener((obs, oldText, newText) -> {
                ArrayList<String> array2 = new ArrayList<>();
                if(!(eid.getText() == null)){
                    ArrayList<String> arrayz = driver.loadInfo();
                    clarityArrWD(arrayz);
                    for(int i=0; i<clarityArrayWD.size(); i++){
                        if(clarityArrayWD.get(i).toLowerCase().contains(newText.toLowerCase())){
                            array2.add(clarityArrayWD.get(i));

                        }
                    }
                    workerList.setItems(FXCollections.observableArrayList(array2));
                }
            });
        }
    }

    private ArrayList readEmployee(){


        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    String loginDetails[] = x.split(":",5);
                    if(loginDetails[0].equals(businessID)){
                        ArrayList<String> test = new ArrayList<>();
                        test.add(loginDetails[1]);
                        test.add(loginDetails[2]);
                        employee.add(test);
                    }


                }

                //prints error
            } catch (IOException e) {
                // e.printStackTrace();
            }

            //file cannot be found
        } catch (FileNotFoundException e) {

        }

        return employee;

    }
}
