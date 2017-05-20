package Gui.businessMenu;

import bookings.Customise;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Martin on 14/05/2017.
 */
public class customizeMenuController {
    private businessMenuController bc;
    Customise c = new Customise();

    @FXML
    private TextField title;
    @FXML
    private TextField b;
    @FXML
    private TextField viewb;

    public static String businessID;

    public static void setBusinessID(String bid){
        businessID = bid;

    }
    @FXML
    private Label label;

    @FXML
    void save(ActionEvent event) throws IOException {
        if(title.getText()!=null){

        }

    }
    public void startChoose(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("customiseMenu.fxml"));
        Customise instance = c.getCustom(businessID);

        if(instance!=null){
            title.setText(c.getMenuName());
            b.setText(c.getBooking());
            viewb.setText(c.getViewing());
        }
        title.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {


            }
        });


        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }

    /*
   * return to business menu
   */
    @FXML
    void back(ActionEvent event) throws IOException {
        //Passes to business Menu
        passToBusinessMenu("businessMenu.fxml", businessID);
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);



    }
    private void passToBusinessMenu(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();
        businessMenuController controller = loader.getController();
        businessMenuController.setBusinessID(businessID);

    }
    /*
     * Update business owner information

    private void update(String abc){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(abc);
                label.getChildrenUnmodifiable();
            }
        });
    }  */


}
