package Gui.customerMenu;


import coreFunctions.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.Login;

import java.io.IOException;

public class customerMenuController {
    @FXML
    private Label employeeID;


    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;
    public static String customerID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }

    public String  getBusinessID(){
        return businessID;

    }
    public static void setCustomerID(String cid){
        customerID = cid;

    }

    public String  getCustomerID(){
        return customerID;

    }


    @FXML
    public void initializing(ActionEvent event) {
        System.out.println(businessID);
        //employeeID.setText(driver.generateEmployeeNo());
    }
    @FXML
    private void switchToBookAppointment(ActionEvent event) throws IOException {
        pass("makeBooking.fxml");
        Parent home_page = FXMLLoader.load(getClass().getResource("makeBooking.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void switchToViewCustomerBookings(ActionEvent event) throws IOException {
        pass("viewBookings.fxml");
        Parent home_page = FXMLLoader.load(getClass().getResource("viewBookings.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML //exit system
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }



    private void pass(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        bookingController controller = loader.getController();
        controller.setBusinessID(businessID);
        controller.setCustomerID(customerID);

    }
    /*
    @FXML
    private TextField input;

    @FXML
    void choice(ActionEvent event) throws IOException {
        try{
            int in = Integer.parseInt(input.getText());
            switch (in) {
                case 1: //View available days/times (to book an appointment for a business)
                    availableBookings(bID);
                    continue;
                case 2:
                    System.out.println("Current Bookings: ");
                    String b = "b"+Integer.toString(bID+1);
                    // System.out.println(b);
                    driver.viewBookingsCustomer(username.getText(),b); //view current bookings
                    continue;
                case 3:
                    System.out.println("Successfully logged out of the system!");
                    System.exit(0);
                    continue;
                default:
                    System.out.println("Invalid numeric input!");
                    continue;
            }
        }
        catch(NumberFormatException e){

        }

    }*/


}
