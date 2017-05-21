package Gui.businessMenu;

import coreFunctions.Driver;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /*
     * get all days/times an employee is working
     */
    private ArrayList clarityArrWD(ArrayList<String> array){
        clarityArrayWD.clear();
        for(int i=0; i<array.size(); i++){

            for(int j=0 ; j<employee.size(); j++){

                if(array.get(i).contains(employee.get(j).get(0)) && array.get(i).contains(businessID)){
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
    private Label eidError;

    @FXML
    private Label dayError;

    //return to business menu
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


    //go back to business menu, storing the business id
    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        businessMenuController.setBusinessID(businessID);

    }

    //remove all shifts for a given day that an employee is working
    @FXML
    void removeWorkday(ActionEvent event) {
        driver.deleteEmployeeWorktimes(businessID, eid.getText(), day.getText());
        ArrayList<String> array = driver.loadInfo();
        clarityArrWD(array);
        workerList.setItems(FXCollections.observableArrayList(clarityArrayWD));


    }

    //function to initialise all information related to days/times an employee is working
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eidError.setVisible(false);
        dayError.setVisible(false);
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

        //CHECK FOR Employee ID
        eid.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(listenerEIDCheck(newText)){
                    eidError.setVisible(false);
                }else{
                    eidError.setVisible(true);
                }
            }
            else{
                eidError.setVisible(false);
            }
        });

//CHECK FOR DAY validity
        day.textProperty().addListener((obs, oldText, newText) -> {
            if(!(oldText == null)){
                if(listenerCheckDay(newText)){
                    dayError.setVisible(false);
                }else{
                    dayError.setVisible(true);
                }
            }
            else{
                dayError.setVisible(false);
            }
        });
    }

    //load all employee information for a given business
    private ArrayList readEmployee(){


        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    String loginDetails[] = x.split(":",5);
                    if(loginDetails[0].equals(businessID)){ //checks that the employee if of that business
                        System.out.println(businessID);
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

    //check employee id validity
    private boolean listenerEIDCheck(String empeid){

        for(int i=0; i<employee.size(); i++){
            if(employee.get(i).get(0).equals(eid.getText())){
                return true;
            }
        }

        return false;
    }

    //checks the validity of a given day
    private boolean listenerCheckDay(String day){
        try{
            DateFormat time = new SimpleDateFormat("EEEE");
            time.parse(day);
        }
        catch(ParseException e){
            return false;
        }
        return true;
    }
}
