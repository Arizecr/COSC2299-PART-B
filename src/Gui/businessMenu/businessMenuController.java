package Gui.businessMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by xemorth on 26/04/2017.
 */
public class businessMenuController {

    @FXML
    private void switchToAddEmployee(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //function to log out
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
