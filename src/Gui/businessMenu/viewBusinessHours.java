package Gui.businessMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.BusinessMenu;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by xemorth on 28/04/2017.
 */
public class viewBusinessHours implements Initializable{
    //

    BusinessMenu bMenu = new BusinessMenu();
    public static String businessID;
    public ArrayList<String> start = new ArrayList<>();
    public ArrayList<String> end = new ArrayList<>();
    public ArrayList<String> days = new ArrayList<>();

    public static void setBusinessID(String busid){
        businessID = busid;
        System.out.println(businessID);
    }


    @FXML
    private TextField mondayStart;

    @FXML
    private TextField mondayEnd;

    @FXML
    private TextField tuesdayStart;

    @FXML
    private TextField tuesdayEnd;

    @FXML
    private TextField wednesdayStart;

    @FXML
    private TextField wednesdayEnd;

    @FXML
    private TextField thursStart;

    @FXML
    private TextField thursEnd;

    @FXML
    private TextField friStart;

    @FXML
    private TextField friEnd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        printFile();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");

        mondayStart.setText(start.get(0));
        tuesdayStart.setText(start.get(1));
        wednesdayStart.setText(start.get(2));
        thursStart.setText(start.get(3));
        friStart.setText(start.get(4));

        mondayEnd.setText(end.get(0));
        tuesdayEnd.setText(end.get(1));
        wednesdayEnd.setText(end.get(2));
        thursEnd.setText(end.get(3));
        friEnd.setText(end.get(4));



    }

    public void printFile(){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";
        try {
            br = new BufferedReader(new FileReader("businessdaysList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    bId = Details[0];
                    day = Details[1];
                    starttime = Details[2];
                    endtime = Details[3];
                    day = day.substring(0,1).toUpperCase() + day.substring(1);
                   start.add(starttime);
                   end.add(endtime);
                }
                //prints error
            } catch (IOException e) {

            }
            catch (ArrayIndexOutOfBoundsException ae) {

            }
            //file cannot be found
        } catch (FileNotFoundException e) {
        }
    }

    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        businessMenuController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setBusinessID(parameterToPass);

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //to update business hours laters
    public boolean updateBusinessHours() {
        String[] start = {mondayStart.getText(), tuesdayStart.getText(),wednesdayStart.getText(),thursStart.getText(), friStart.getText()};
        String[] end = {mondayEnd.getText(), tuesdayEnd.getText(),wednesdayEnd.getText(),thursEnd.getText(), friEnd.getText()};

        int valid = 0;
        for(int i=0; i<start.length; i++){
            if(bMenu.timeCheck(start[i], end[i])){

            }

            else{

                valid++;
            }


        }

        if(valid == start.length){
            try {
                FileWriter fw = new FileWriter("businessdaysList.txt"); //the true will append the new data

                //replace b1 with businessid
                fw.write(businessID + " Monday " + mondayStart.getText()+ " " +mondayEnd.getText()+"\n");
                fw.write(businessID + " Tuesday " + tuesdayStart.getText()+ " " +tuesdayEnd.getText()+"\n");
                fw.write(businessID + " Wednesday " + wednesdayStart.getText()+ " " +wednesdayEnd.getText()+"\n");
                fw.write(businessID + " Thursday " + thursStart.getText()+ " " +thursEnd.getText()+"\n");
                fw.write(businessID + " Friday " + friStart.getText()+ " " +friEnd.getText()+"\n");
                fw.close();
            } catch (IOException ioe) {
            }
            return true;

        }
        else {
            return false;
        }

    }

    @FXML
    void updateHours(ActionEvent event) throws IOException{
            if(updateBusinessHours()){
                pass("businessMenu.fxml", businessID);
                switchToBusinessMenu(event);

            }

            else{

            }



    }

}
