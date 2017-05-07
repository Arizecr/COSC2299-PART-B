package Gui.businessMenu;

import coreFunctions.WriteToFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    WriteToFile w =new WriteToFile();
    BusinessMenu bMenu = new BusinessMenu();
    public static String businessID;
    public ArrayList<String> start = new ArrayList<>();
    public ArrayList<String> end = new ArrayList<>();
    public ArrayList<String> days = new ArrayList<>();
    public ArrayList<String> allbdays = new ArrayList<>();

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

    /*
     * initialise current opening hours
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        printFile();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        if(start.size()!=0) {
            mondayStart.setText(start.get(0));
            tuesdayStart.setText(start.get(1));
            wednesdayStart.setText(start.get(2));
            thursStart.setText(start.get(3));
            friStart.setText(start.get(4));
        }
        if(end.size()!=0) {
            mondayEnd.setText(end.get(0));
            tuesdayEnd.setText(end.get(1));
            wednesdayEnd.setText(end.get(2));
            thursEnd.setText(end.get(3));
            friEnd.setText(end.get(4));
        }


    }

    /*
     * retrieve current opening hours information
     */
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

                    if(bId.equals(businessID)) //load in information for the current business
                    {
                        start.add(starttime);
                        end.add(endtime);
                    }
                    allbdays.add(x);
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

    /*
     * store current business id information
     */
    private void pass(String fxmlFile, String parameterToPass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();

        //MUST change classname to the file u want to pass the variable to
        businessMenuController controller = loader.getController();

        //function in the controller u go must contain this
        controller.setBusinessID(parameterToPass);

    }

    private void replaceDay(String s,String r){
        for(int i=0; i < allbdays.size() ;i++){
            if(allbdays.get(i).contains(s)){
                allbdays.set(i,r);
            }
        }

    }
    private boolean bExists(){
        for(int i=0; i < allbdays.size() ;i++){
            if(allbdays.get(i).contains(businessID)){
                return true;
            }
        }
        return false;
    }

    /*
     * cancel function, go back to main menu
     */
    @FXML
    void cancel(ActionEvent event) throws IOException {
        pass("businessMenu.fxml", businessID);
        switchToBusinessMenu(event);

    }

    /*
     * go back to main menu
     */
    private void switchToBusinessMenu(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("businessMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    //to update business hours
    public boolean updateBusinessHours() {
        String[] start = {mondayStart.getText(), tuesdayStart.getText(),wednesdayStart.getText(),thursStart.getText(), friStart.getText()};
        String[] end = {mondayEnd.getText(), tuesdayEnd.getText(),wednesdayEnd.getText(),thursEnd.getText(), friEnd.getText()};

        int valid = 0;
        for(int i=0; i<start.length; i++){
           if(start[i]!=null&&end[i]!=null) {
               if (bMenu.timeCheck(start[i], end[i])) {

               } else {

                   valid++;
               }
           }

        }


        if(valid == start.length){
            try {
                FileWriter fw = new FileWriter("businessdaysList.txt"); //the true will append the new data
                if(bExists()){
                    //replace b1 with businessid
                    replaceDay((businessID + " Monday"),(businessID + " Monday " + mondayStart.getText()+ " " +mondayEnd.getText()));
                    replaceDay(businessID + " Tuesday",(businessID + " Tuesday " + tuesdayStart.getText()+ " " +tuesdayEnd.getText()));
                    replaceDay(businessID + " Wednesday ",businessID + " Wednesday " + wednesdayStart.getText()+ " " +wednesdayEnd.getText());
                    replaceDay(businessID + " Thursday " ,businessID + " Thursday " + thursStart.getText()+ " " +thursEnd.getText());
                    replaceDay(businessID + " Friday ",businessID + " Friday " + friStart.getText()+ " " +friEnd.getText());

                }
                else{//this is a new business
                    allbdays.add(businessID + " Monday " + mondayStart.getText()+ " " +mondayEnd.getText());
                    allbdays.add(businessID + " Tuesday " + tuesdayStart.getText()+ " " +tuesdayEnd.getText());
                    allbdays.add(businessID + " Wednesday " + wednesdayStart.getText()+ " " +wednesdayEnd.getText());
                    allbdays.add(businessID + " Thursday " + thursStart.getText()+ " " +thursEnd.getText());
                    allbdays.add(businessID + " Friday " + friStart.getText()+ " " +friEnd.getText());

                }
                fw.close();
            } catch (IOException ioe) {
            }
            w.rewriteToFile(allbdays, "businessdaysList.txt");
            return true;

        }
        else {
            return false;
        }

    }

    /*
     * update hours
     */
    @FXML
    void updateHours(ActionEvent event) throws IOException{
        if(updateBusinessHours()){
            pass("businessMenu.fxml", businessID);
            switchToBusinessMenu(event);

        }

        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All days must be set");
        }



    }

}
