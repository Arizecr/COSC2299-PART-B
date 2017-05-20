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
        c.loadCustom();
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("customizeMenu.fxml"));
        Customise instance = c.getCustom(businessID);
        System.out.println(instance);
        if(instance!=null) {
            if (instance.getMenuName() != null) {
                title.setText(instance.getMenuName());
            }
            if (instance.getBooking() != null) {
                b.setText(instance.getBooking());
            }
            if (instance.getViewing() != null) {
                viewb.setText(instance.getViewing());
            }
        }

        title.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Customise instance = c.getCustom(businessID);
                if(instance!=null){
                    instance.setMenuName(title.getText());
                }
                else {
                    instance = new Customise(businessID,title.getText(),null,null,null);
                }
                c.addCustom(businessID,instance);

            }
        });
        b.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Customise instance = c.getCustom(businessID);
                if(instance!=null){
                    instance.setBooking(b.getText());
                }
                else {
                    instance = new Customise(businessID,null,b.getText(),null,null);
                }
                c.addCustom(businessID,instance);

            }
        });
        viewb.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Customise instance = c.getCustom(businessID);
                if(instance!=null){
                    instance.setViewing(viewb.getText());
                }
                else {
                    instance = new Customise(businessID,null,null,viewb.getText(),null);
                }
                c.addCustom(businessID,instance);

            }
        });

        ((Pane) rootNode).getChildren().add(title);
        ((Pane) rootNode).getChildren().add(b);
        ((Pane) rootNode).getChildren().add(viewb);

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
