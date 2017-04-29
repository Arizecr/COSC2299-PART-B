package Gui.customerMenu;

import bookings.Services;
import coreFunctions.Driver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import menu.BusinessMenu;
import menu.Login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yesmi on 28/04/2017.
 */

public class bookingController {
    Login loginMenu = new Login();
    Services s = new Services();
    Login login = new Login();
    Driver driver = new Driver();
    Services selectedS = new Services(businessID,null,null,null,null);
    public static String businessID;
    public static String customerID;
    public ArrayList<String> days = new ArrayList<>();
    public ArrayList<DayOfWeek> date = new ArrayList<>();
    BusinessMenu bm = new BusinessMenu();

    @FXML
    public DatePicker bdate;

    @FXML
    private ChoiceBox c;

    @FXML
    private TextField starttime;

    @FXML
    private TextField endtime;

    public static void setBusinessID(String bid){
        businessID = bid;

    }


    public static void setCustomerID(String cid){
        customerID = cid;

    }
    public void startMakeBook(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("makeBooking.fxml"));
        ArrayList<String> services = new ArrayList<>();
        //ChoiceBox c = new ChoiceBox();
        s.printService(businessID);
        //ArrayList<Button> b = new ArrayList<>();
        // AnchorPane root = new AnchorPane();
        for(int i=0;i<s.serviceList.size();i++){
            //makes sure the services of only the current business are displayed
            if(businessID.equals(s.serviceList.get(i).b())){
                String n = s.serviceList.get(i).getName() +" - length: ";
                String l = s.serviceList.get(i).getLengthT();
                String Time[] = l.split("-", 2);
                String hours = Time[0];
                String min = Time[1];
                //final format of string in the list
                n+= hours +" Hours and " +min +" Minutes ($" + s.serviceList.get(i).getCost()+")";

                services.add(n);


            }

        }
        c.setItems(FXCollections.observableArrayList(services));
        c.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //this service is saved to the class for use in adding end time of service
                selectedS = s.serviceList.get(newValue.intValue());

            }
        });
        c.setTooltip(new Tooltip("Select the service"));
        //print services service
        //select date and time


        starttime.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    if(bm.checktime(starttime.getText())){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid time format.In the form HH:30 or HH:00 only\n Try again.");
                        alert.showAndWait();
                    }
                    else{
                        //do end time
                        DateFormat time = new SimpleDateFormat("HH:mm");
                        Date start = Calendar.getInstance().getTime() ;
                        String st = starttime.getText();
                        try{
                            start = time.parse(st);
                        }catch(ParseException e){}
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(start);
                        //gets the time of the service
                        String toAdd = selectedS.getLengthT();
                        String Time[] = toAdd.split("-", 2);
                        String hours = Time[0];
                        String min = Time[1];
                        int h = Integer.parseInt(hours);
                        int m = Integer.parseInt(min);
                        cal.add(Calendar.MINUTE,m );
                        cal.add(Calendar.HOUR,h );
                        String newTime = time.format(cal.getTime());
                        endtime.setText(newTime);
                    }
                }
            }
        });
        starttime.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!bm.checktime(starttime.getText()))
                {
                    //do end time
                    DateFormat time = new SimpleDateFormat("HH:mm");
                    Date start = Calendar.getInstance().getTime() ;
                    String st = starttime.getText();
                    try{
                        start = time.parse(st);
                    }catch(ParseException e){}
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(start);
                    //gets the time of the service
                    String toAdd = selectedS.getLengthT();
                    String Time[] = toAdd.split("-", 2);
                    String hours = Time[0];
                    String min = Time[1];
                    int h = Integer.parseInt(hours);
                    int m = Integer.parseInt(min);
                    cal.add(Calendar.MINUTE,m );
                    cal.add(Calendar.HOUR,h );
                    String newTime = time.format(cal.getTime());
                    endtime.setText(newTime);

                }
            }
        });
        checkFile();
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        bdate.setDayCellFactory(dayCellFactory);
        ((AnchorPane) rootNode).getChildren().add(bdate);
        ((AnchorPane) rootNode).getChildren().add(starttime);
        ((AnchorPane) rootNode).getChildren().addAll(c);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }
    /* public enum DayOfWeek {
         Monday(DayOfWeek.MONDAY),Tuesday(DayOfWeek.MONDAY),Wednesday(3),Thursday(4),Friday(5),Saturday(6),Sunday(7);

         private final DayOfWeek value;

         DayOfWeek(DayOfWeek value) {

             this.value = value;
         }

         public DayOfWeek getValue() {

             return value;
         }

         @Override
         public String toString() {

             return value + "";
         }
     }*/
    public void checkFile(){
        days = new ArrayList<>();
        date = new ArrayList<>();
        BufferedReader br;
        String bId= "" ;
        String day ="" ;

        try {
            br = new BufferedReader(new FileReader("businessdaysList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    bId = Details[0];
                    day = Details[1];
                    day = day.substring(0,1).toUpperCase() + day.substring(1);
                    if(bId.equals(businessID)&& !days.contains(day)) {
                        days.add(day);

                    }

                }
                //this removes the days the business is not open from the calendar
                if (!(days.contains("Monday"))){date.add(DayOfWeek.MONDAY);}
                if (!(days.contains("Tuesday"))){date.add(DayOfWeek.TUESDAY);}
                if (!(days.contains("Wednesday"))){date.add(DayOfWeek.WEDNESDAY);}
                if (!(days.contains("Thursday"))){date.add(DayOfWeek.THURSDAY);}
                if (!(days.contains("Friday"))){date.add(DayOfWeek.FRIDAY);}
                if (!(days.contains("Saturday"))){date.add(DayOfWeek.SATURDAY);}
                if (!(days.contains("Sunday"))){date.add(DayOfWeek.SUNDAY);}
                //prints error
            } catch (IOException e) {

            }
            catch (ArrayIndexOutOfBoundsException ae) {

            }
            //file cannot be found
        } catch (FileNotFoundException e) {
        }
    }
    private Callback<DatePicker, DateCell> getDayCellFactory() {
        Calendar c = Calendar.getInstance();
        int dayOfYear = c.get(Calendar.DAY_OF_YEAR);
        int Year = c.get(Calendar.YEAR);
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (date.contains(item.getDayOfWeek())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                        if (item.getDayOfYear()<dayOfYear||item.getDayOfYear()>(dayOfYear+30))
                        {

                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                        if (item.getYear()!=Year&&item.getDayOfYear()<=(dayOfYear+30))
                        {

                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    public void startViewBook(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("viewBookings.fxml"));
        driver.loadCurrentBookings(businessID);
        ListView<String> list = new ListView<String>();
        list.disabledProperty();
        list.setLayoutX(60);
        list.setLayoutY(94);
        list.setMaxHeight(298);
        list.setMaxWidth(245);
        ArrayList<String> bookings = new ArrayList<>();

        for(int i=0;i<driver.currentBookings.size();i++){
            if(driver.currentBookings.get(i).getCustomerID().equals(customerID)){
                String s = "Date: " + driver.currentBookings.get(i).getDate();
                s+="\nDay: " + driver.currentBookings.get(i).getDayBooked();
                s+="\nTime: " + driver.currentBookings.get(i).getTimeBooked();
                s+="\nService: " + driver.currentBookings.get(i).getServiceBooked();
                bookings.add(s);
            }

        }
        ObservableList<String> data = FXCollections.observableArrayList(bookings);
        //ArrayList<Button> b = new ArrayList<>();
        // AnchorPane root = new AnchorPane();

        //print the customers bookings by date
        ((AnchorPane) rootNode).getChildren().add(new Pane());

        ((AnchorPane) rootNode).getChildren().addAll(list);

        list.setItems(data);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }
    public void cancel(ActionEvent event) throws IOException {
        Parent home_page = FXMLLoader.load(getClass().getResource("customerMenu.fxml"));
        Scene home_page_scene = new Scene(home_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

/*    public void checkStart(ActionEvent event) throws IOException {
        if(bm.checktime(starttime.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid time format.In the form HH:30 or HH:00 only\n Try again.");
            alert.showAndWait();
        }
    }*/
}
