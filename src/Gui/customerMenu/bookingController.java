package Gui.customerMenu;

import coreFunctions.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import menu.Login;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yesmi on 28/04/2017.
 */

public class bookingController {
    Login loginMenu = new Login();

    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;
    public static String customerID;
    public static void setBusinessID(String bid){
        businessID = bid;

    }


    public static void setCustomerID(String cid){
        customerID = cid;

    }
    public void startMakeBook(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("makeBooking.fxml"));


        //ArrayList<Button> b = new ArrayList<>();
        // AnchorPane root = new AnchorPane();


        //print services service
        //select date and time


        //     ((AnchorPane) rootNode).getChildren().add(gridButtons);

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }


    public void startViewBook(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("viewBookings.fxml"));
        driver.loadCurrentBookings(businessID);
        ListView<String> list = new ListView<String>();
        ArrayList<String> bookings = new ArrayList<>();

        for(int i=0;i<driver.currentBookings.size();i++){
            if(driver.currentBookings.get(i).getCustomerID().equals(customerID)){
                String s = "Date: " + driver.currentBookings.get(i).getDate();
                s+="\nDay: " + driver.currentBookings.get(i).getDayBooked();
                s+="\nTime: " + driver.currentBookings.get(i).getTimeBooked();
                s+="\nService: " + driver.currentBookings.get(i).getServiceBooked();
                bookings.add(s);
            }

        }
        ObservableList<String> data = FXCollections.observableArrayList(bookings);
        //ArrayList<Button> b = new ArrayList<>();
        // AnchorPane root = new AnchorPane();

        //print the customers bookings by date

        ((AnchorPane) rootNode).getChildren().addAll(list);

        list.setItems(data);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("customerMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
