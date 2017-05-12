package Gui.businessMenu;

import coreFunctions.Driver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.Login;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yesmi on 28/04/2017.
 */
public class chooseCustomerController {
    @FXML
    private Label employeeID;
    Login loginMenu = new Login();

    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;
    public static String customerID;
    @FXML
    private Pane child;

    ListView l = new ListView();
    public static Boolean selected;

    //set business id
    public static void setBusinessID(String cid){
        businessID = cid;

    }


    /*
     * pass business id throughout functions
     */
    private void pass(String fxmlFile, String parameterToPass1,String parameterToPass2) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            //MUST change classname to the file u want to pass the variable to
            businessBookingController controller = loader.getController();

            //function in the controller u go must contain this
            controller.setBusinessID(parameterToPass1);
            controller.setCustomerID(parameterToPass2);
        }catch(IOException e){}

    }


    /*
     * business chooses customer, when making a booking
     */
    public void startChoose(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("chooseCustomer.fxml"));
        selected = false;
        loginMenu.loadCustomerInformation();
        ArrayList<String> b = new ArrayList<>();
        for(int i=0;i<login.customerList.size();i++){
            b.add("("+login.customerList.get(i).getUsername()+ ") "+ login.customerList.get(i).getName());
        }

        l.setItems(FXCollections.observableArrayList(b));
        l.setLayoutX(20.0);
        l.setLayoutY(50);
        l.setMinWidth(370);
        l.setMaxHeight(300);
        l.prefHeight(100.0);
        ((AnchorPane) rootNode).getChildren().add(l);
        l.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                for(int i=0;i<login.customerList.size();i++){
                    if(newValue.equals("("+login.customerList.get(i).getUsername()+ ") "+ login.customerList.get(i).getName())){
                        pass("businessMakeBooking.fxml",businessID,login.customerList.get(i).getUsername()) ;
                        System.out.println(login.customerList.get(i).getUsername());

                    }
                }
                selected = true;
                //switchToMenu(event);
            }
        });
        //ArrayList<Button> b = new ArrayList<>();
  /*      AnchorPane root = new AnchorPane();
        int h = 200/(login.customerList.size()+1);
        int next = 200/(login.customerList.size()+1)+10;
        double count = 30;
        //gets all the names of all business's registered to the system
        for(int i=0;i<login.customerList.size();i++){
            count+=next;
            Button gridButtons = new Button();
            gridButtons.setText(login.customerList.get(i).getUsername()+ " "+ login.customerList.get(i).getName());
            gridButtons.setMnemonicParsing(false);
            gridButtons.prefHeight(50.0);
            gridButtons.prefWidth(500.0);

            gridButtons.setId(login.customerList.get(i).getUsername());
            gridButtons.setLayoutX(20.0);
            gridButtons.setMinWidth(370);
            gridButtons.setMinHeight(20);
            gridButtons.setLayoutY(count);

            gridButtons.setOnAction( new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    pass("businessMakeBooking.fxml",businessID,gridButtons.getId()) ;
                    switchToMenu(event);
                }
            });


            ((AnchorPane) rootNode).getChildren().add(gridButtons);
        }*/

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }

    /*
     * go to make booking scene
     */
    @FXML
    private void switchToMenu(ActionEvent event)  {

        if(selected)
        {
            try {
                Parent home_page = FXMLLoader.load(getClass().getResource("businessMakeBooking.fxml"));
                Scene home_page_scene = new Scene(home_page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //app_stage.setScene(home_page_scene);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("businessMakeBooking.fxml"));
                Pane pane = loader.load();
                //MUST change classname to the file u want to pass the variable to
                businessBookingController controller = loader.getController();
                //function in the controller u go must contain this
                controller.startMakeBook(app_stage);


            }
            catch(IOException e){}}

    }

    @FXML //exit system
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    //go back to business menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
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
