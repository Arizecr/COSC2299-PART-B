package Gui.businessMenu;

import javafx.application.Platform;
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
        update(title.getText());
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
     */
    private void update(String abc){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(abc);
                label.getChildrenUnmodifiable();
            }
        });
    }


}
