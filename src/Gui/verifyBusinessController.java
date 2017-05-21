package Gui;

import coreFunctions.Driver;
import coreFunctions.WriteToFile;
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
import user.Business;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yesmi on 12/05/2017.
 */
public class verifyBusinessController {
    @FXML
    private Label employeeID;
    Login loginMenu = new Login();

    Login login = new Login();
    Driver driver = new Driver();
    public static String businessID;
    public static boolean selected;
    public static Business selectedB;
    WriteToFile w = new WriteToFile();
    @FXML
    private Pane child;

    public void startChoose(Stage stage) throws IOException {
        selected = false;
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = FXMLLoader.load(getClass().getResource("verifyBusiness.fxml"));
        loginMenu.loadOwnerInformation();
        ArrayList<String> b = new ArrayList<>();
        for(int i = 0; i< Login.pendingList.size(); i++){
            b.add(Login.pendingList.get(i).getBusinessName()+" ("+ Login.pendingList.get(i).getUsername()+")");
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


                for(int i = 0; i< Login.pendingList.size(); i++){
                    if(newValue.equals(Login.pendingList.get(i).getBusinessName()+" ("+ Login.pendingList.get(i).getUsername()+")")){
                        selectedB = Login.pendingList.get(i);
                        selected = true;
                    }
                }

                //switchToMenu(event);
            }
        });


        //gets all the names of all business's registered to the system
    /*    for(int i=0;i<login.pendingList.size();i++){
            count+=next;
            Button gridButtons = new Button();
            gridButtons.setText(login.pendingList.get(i).getBusinessName());
            gridButtons.setMnemonicParsing(false);
            gridButtons.prefHeight(50.0);
            gridButtons.prefWidth(500.0);

            gridButtons.setId(login.pendingList.get(i).getUsername());
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
    private void validate(ActionEvent event)  {
        try {
            if(selected) {
                Login.businessList.add(selectedB);
                for(int i = 0; i< Login.pendingList.size(); i++){
                    if(selectedB.equals(Login.pendingList.get(i))){
                        Login.pendingList.remove(i);
                    }
                }
                w.rewriteToFile(Login.businessList,"business.txt");
                w.rewriteToFile(Login.pendingList,"businessPending.txt");
                Parent home_page = FXMLLoader.load(getClass().getResource("chooseBusiness.fxml"));
                Scene home_page_scene = new Scene(home_page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //app_stage.setScene(home_page_scene);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseBusiness.fxml"));
                Pane pane = loader.load();
                //MUST change classname to the file u want to pass the variable to
                chooseBusinessController controller = loader.getController();
                //function in the controller u go must contain this
                controller.startChoose(app_stage);
            }
        }
        catch(IOException e){}

    }


    //go back to admin menu
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("chooseBusiness.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.setScene(home_page_scene);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseBusiness.fxml"));
        Pane pane = loader.load();
        //MUST change classname to the file u want to pass the variable to
        chooseBusinessController controller = loader.getController();
        //function in the controller u go must contain this
        controller.startChoose(app_stage);
    }

}
