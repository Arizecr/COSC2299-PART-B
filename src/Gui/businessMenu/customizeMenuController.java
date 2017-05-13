package Gui.businessMenu;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;

/**
 * Created by Martin on 14/05/2017.
 */
public class customizeMenuController {
    private businessMenuController bc;

    @FXML
    private TextField title;

    @FXML
    private Label label;

    @FXML
    void save(ActionEvent event) throws IOException {

        update(title.getText());





/*
        if(title.getText() != null){
            businessMenuController.setBusinessName("sas");

        }*/



    }

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
