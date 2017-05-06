package Gui.businessMenu;

import EmployeeAvailabilityDays.AvailableDay;
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
import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class showWorkerAvailibilityController implements Initializable{
    AvailableDay ad = new AvailableDay();
    ArrayList<ArrayList<String>> employee = new ArrayList<ArrayList<String>>();
    ArrayList<String> clarityArrayAD = new ArrayList<>();
    Employee emp = new Employee();

    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }


    @FXML
    private ListView<String> workerList;

    @FXML
    private TextField name;

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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("dwlo");
        readEmployee();
        ad.loadInfo(businessID);
        ArrayList<String> array = ad.Bavailability;

        clarityArrAD(array,null);


        workerList.setItems(FXCollections.observableArrayList(clarityArrayAD));

        name.textProperty().addListener((obs, oldText, newText) -> {
            ArrayList<String> array2 = new ArrayList<>();
            ArrayList<String> arrayz = ad.Bavailability;
            if(!(name.getText() == null)){


                clarityArrAD(arrayz,newText);

            }
            else{ clarityArrAD(arrayz,null);}
            for(int i=0; i<clarityArrayAD.size(); i++){
                if(clarityArrayAD.get(i).toLowerCase().contains(newText.toLowerCase())){
                    array2.add(clarityArrayAD.get(i));

                }
            }
            workerList.setItems(FXCollections.observableArrayList(array2));
        });


    }

   /* private ArrayList clarityArrAD(ArrayList<String> array){
        clarityArrayAD.clear();
        for(int i=0; i<array.size(); i++){

            for(int j=0 ; j<employee.size(); j++){

                if(array.get(i).contains(employee.get(j).get(0))){
                    String[] arrayinfo = array.get(i).split(" ", 5);
                    clarityArrayAD.add(employee.get(j).get(1) + "(" + arrayinfo[1] + ")" + " " + arrayinfo[2] + " Start: " + arrayinfo[3] + " End: " + arrayinfo[4]);
                }
            }

        }


        return clarityArrayAD;
    }
*/
   private ArrayList clarityArrAD(ArrayList<String> array, String e){
       clarityArrayAD.clear();
       for(int i=0; i<array.size(); i++){
           String arrayinfo[] = array.get(i).split(" ",5);
           String bID = arrayinfo[0];
           String eID = arrayinfo[1];
           String name = emp.getEmployeeName(bID, eID);
           if(e!=null && (array.get(i).contains(e)||e.equals(eID)||name.contains(e))) {


               clarityArrayAD.add(name + "(" + arrayinfo[1] + ")" + " " + arrayinfo[2] + " Start: " + arrayinfo[3] + " End: " + arrayinfo[4]);
           }
           else if(e==null ) {

               clarityArrayAD.add(name + "(" + arrayinfo[1] + ")" + " " + arrayinfo[2] + " Start: " + arrayinfo[3] + " End: " + arrayinfo[4]);
           }
       }




       return clarityArrayAD;
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
