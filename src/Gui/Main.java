package Gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*
     * displays login screen
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Booking System Application");
        primaryStage.setScene(new Scene(root, 480, 350));
        primaryStage.show();

    }

    //launch program
    public static void main(String[] args) {
        launch(args);
    }


}
