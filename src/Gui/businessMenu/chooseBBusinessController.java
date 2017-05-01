package Gui.businessMenu;

import coreFunctions.Driver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.Login;

import java.io.IOException;

/**
 * Created by yesmi on 28/04/2017.
 */
public class chooseBBusinessController {
    @FXML
    private Label employeeID;
    Login loginMenu = new Login();

    Login login = new Login();
    Driver driver = new Driver();
    public static String bbusinessID;
    public static String customerID;
    @FXML
    private Pane child;



    public static void setBBusinessID(String cid){
        bbusinessID = cid;

    }

    public String  getCustomerID(){
        return customerID;

    }
    private void pass(String fxmlFile, String parameterToPass1) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            //MUST change classname to the file u want to pass the variable to
            businessMenuController controller = loader.getController();

            //function in the controller u go must contain this
            controller.setBusinessID(parameterToPass1);

        }catch(IOException e){}

    }


    public void startChoose(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("chooseBBusiness.fxml"));

        loginMenu.loadOwnerInformation();
        //ArrayList<Button> b = new ArrayList<>();
        AnchorPane root = new AnchorPane();
        double count = 30;

        //gets all the names of all business's registered to the system
        for(int i=0;i<login.businessList.size();i++){
            for(int j=0;j<login.businessOwnerList.size();j++){
             if(bbusinessID.equals(login.businessOwnerList.get(j).getUsername())) {
                 if(login.businessOwnerList.get(j).getAllB().contains(login.businessList.get(i))){
                     count+=70;
                     Button gridButtons = new Button();
                     gridButtons.setText(login.businessList.get(i).getName());
                     gridButtons.setMnemonicParsing(false);
                     gridButtons.prefHeight(50.0);
                     gridButtons.prefWidth(500.0);

                     gridButtons.setId(login.businessList.get(i).getUsername());
                     gridButtons.setLayoutX(20.0);
                     gridButtons.setMinWidth(370);
                     gridButtons.setMinHeight(60);
                     gridButtons.setLayoutY(count);

                     gridButtons.setOnAction( new EventHandler<ActionEvent>() {
                         public void handle(ActionEvent event) {
                             pass("businessMenu.fxml",gridButtons.getId()) ;
                             switchToMenu(event);
                         }
                     });


                     ((AnchorPane) rootNode).getChildren().add(gridButtons);
                 }
             }
            }


        }

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }
    @FXML
    private void switchToMenu(ActionEvent event)  {
        try {

            Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
            Scene home_page_scene = new Scene(home_page);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
        catch(IOException e){}

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
