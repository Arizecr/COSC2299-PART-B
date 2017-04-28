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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import menu.Login;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yesmi on 28/04/2017.
 */
public class chooseBusinessController {
    @FXML
    private Label employeeID;
    Login loginMenu = new Login();

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
    public void startChoose(ActionEvent event) throws IOException {

        loginMenu.loadOwnerInformation();
        ArrayList<Button> b = new ArrayList<>();
        //gets all the names of all business's registered to the system
        for(int i=0;i<login.businessList.size();i++){
            System.out.println(i+". "+login.businessList.get(i).getName());
            Button gridButtons = new Button();
            gridButtons.setText(login.businessList.get(i).getName());
            gridButtons.minWidth(34.0);
            gridButtons.setMnemonicParsing(false);
            gridButtons.prefHeight(38.0);
            gridButtons.prefWidth(41.0);
            gridButtons.setTextAlignment(TextAlignment.CENTER);
           b.add(gridButtons);
        }

    }
    private void pass(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        customerMenuController controller = loader.getController();
        controller.setBusinessID(businessID);
        controller.setCustomerID(customerID);

    }
    @FXML //exit system
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    //go back to business menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
 /*   @Override
    public void initialize(URL location, ResourceBundle resources) {
        getBusinessID();getCustomerID();
    }*/



}
