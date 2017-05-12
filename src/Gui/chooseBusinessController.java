package Gui;

import Gui.businessMenu.businessMenuController;
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
public class chooseBusinessController {
    @FXML
    private Label employeeID;
    Login loginMenu = new Login();

    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;
    public static boolean selected;
    @FXML
    private Pane child;




    private void pass(String fxmlFile, String parameterToPass1) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            //MUST change classname to the file u want to pass the variable to
            businessMenuController controller = loader.getController();

            //function in the controller u go must contain this
            businessMenuController.setBusinessID(parameterToPass1);

        }catch(IOException e){}

    }


    public void startChoose(Stage stage) throws IOException {
        selected = false;
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = FXMLLoader.load(getClass().getResource("chooseBusiness.fxml"));
        loginMenu.loadOwnerInformation();
        ArrayList<String> b = new ArrayList<>();
        for(int i = 0; i< Login.businessList.size(); i++){
            b.add(Login.businessList.get(i).getBusinessName()+" ("+ Login.businessList.get(i).getUsername()+")");
        }
        ListView l = new ListView();
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


                for(int i = 0; i< Login.businessList.size(); i++){
                    if(newValue.equals(Login.businessList.get(i).getBusinessName()+" ("+ Login.businessList.get(i).getUsername()+")")){
                        pass("businessMenu/businessMenu.fxml", Login.businessList.get(i).getUsername()) ;
                        //System.out.println(login.businessList.get(i).getUsername());
                        selected = true;
                    }
                }

                //switchToMenu(event);
            }
        });


        //gets all the names of all business's registered to the system
    /*    for(int i=0;i<login.businessList.size();i++){
            count+=next;
            Button gridButtons = new Button();
            gridButtons.setText(login.businessList.get(i).getBusinessName());
            gridButtons.setMnemonicParsing(false);
            gridButtons.prefHeight(50.0);
            gridButtons.prefWidth(500.0);

            gridButtons.setId(login.businessList.get(i).getUsername());
            gridButtons.setLayoutX(20.0);
            gridButtons.setMinWidth(370);
            gridButtons.setMinHeight(h);
            gridButtons.setLayoutY(count);

            gridButtons.setOnAction( new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    pass("businessMenu/businessMenu.fxml",gridButtons.getId()) ;
                    switchToMenu(event);
                }
            });


            ((AnchorPane) rootNode).getChildren().add(gridButtons);
        }*/

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }
    @FXML
    private void switchToMenu(ActionEvent event)  {
        try {
if(selected) {
    Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu/businessMenu.fxml"));
    Scene home_page_scene = new Scene(home_page);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(home_page_scene);
    app_stage.show();
}
        }
        catch(IOException e){}

    }

    @FXML //admin verify new business details
    public void switchToVerify(ActionEvent event) throws IOException{
        Parent home_page = FXMLLoader.load(getClass().getResource("verifyBusiness.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("verifyBusiness.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
        verifyBusinessController controller = loader.getController();
        //function in the controller u go must contain this
        controller.startChoose(app_stage);
    }
    //go back to login
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
