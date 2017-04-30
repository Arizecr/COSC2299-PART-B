package Gui.businessMenu;

/**
 * Created by asus on 28-Apr-17.
 */

import bookings.Services;
import coreFunctions.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.BusinessMenu;
import menu.Login;
import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class currentBookings  {
    Driver d = new Driver();
    Login loginMenu = new Login();
    Services s = new Services();
    Login login = new Login();
    Employee e = new Employee();
    Driver driver = new Driver();
    BusinessMenu bm = new BusinessMenu();
    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }



    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        controller.setBusinessID(businessID);

    }

    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        viewBookingSummaryController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setBusinessID(parameterToPass);

    }


    public void startViewBook(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("currentBookings.fxml"));
        driver.loadCurrentBookings(businessID);
        driver.checkBookings();
        ListView<String> list = new ListView<String>();
        list.disabledProperty();
        list.setLayoutX(60);
        list.setLayoutY(94);
        list.setMaxHeight(200);
        list.setMaxWidth(245);
        ArrayList<String> bookings = new ArrayList<>();

        for(int i=0;i<driver.currentBookings.size();i++){
            if(driver.currentBookings.get(i).getBusiness().equals(businessID)){
                String s = "Date: " + driver.currentBookings.get(i).getDate();
                s+="\nDay: " + driver.currentBookings.get(i).getDayBooked();
                s+="\nTime: " + driver.currentBookings.get(i).getTimeBooked();
                s+="\nService: " + driver.currentBookings.get(i).getServiceBooked();
                if(e.getEmployeeName(businessID,driver.currentBookings.get(i).getEmployeeID())!=null){
                    s+="\nEmployee: " + e.getEmployeeName(businessID,driver.currentBookings.get(i).getEmployeeID());
                }
                bookings.add(s);
            }

        }
        ObservableList<String> data = FXCollections.observableArrayList(bookings);
        ((AnchorPane) rootNode).getChildren().add(new Pane());

        ((AnchorPane) rootNode).getChildren().addAll(list);

        list.setItems(data);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);


    }

    public void printFile(){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String name ="";
        String time ="";
        String service="";
        String cID ="";
        try {
            br = new BufferedReader(new FileReader("currentBookings.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",6);
                    bId = Details[0];
                    day = Details[1];
                    name = Details[2];
                    time = Details[3];
                    service = Details[4];
                    cID = Details[5];
                    day = day.substring(0,1).toUpperCase() + day.substring(1);

                }
                //prints error
            } catch (IOException e) {

            }
            catch (ArrayIndexOutOfBoundsException ae) {

            }
            //file cannot be found
        } catch (FileNotFoundException e) {
        }
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        //Passes to addEmployeeController

        passToViewBookingSummary("viewBookingSummaryController.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBookingSummaryController.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);


        //System.out.println(busId); //get Businessid

    }

    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    public void switchToSummary(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBookingSummary.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    private void passToViewBookingSummary(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        viewBookingSummaryController controller = loader.getController();
        controller.setBusinessID(parameterToPass);

    }


}
