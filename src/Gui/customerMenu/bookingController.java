package Gui.customerMenu;

import bookings.Services;
import coreFunctions.Driver;
import coreFunctions.WriteToFile;
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
import user.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    Employee e = new Employee();
    Driver driver = new Driver();
    BusinessMenu bm = new BusinessMenu();
    public static Services selectedS = new Services(null,null,null,null,null);
    public static Employee selectedE = new Employee(null,null,null,null,null);
    Services not = new Services(null,null,null,null,null);
    Employee not2 = new Employee(null,null,null,null,null);
    public static String businessID;
    public static String customerID;
    public ArrayList<String> days = new ArrayList<>();
    public ArrayList<DayOfWeek> date = new ArrayList<>();
    public ArrayList<Employee> emp = new ArrayList<>();
    public ArrayList<Services> ser = new ArrayList<>();

    public static LocalDate dateinfo = null;
    public static String startinfo = null;
    public static String endinfo = null;

    @FXML
    public DatePicker bdate;

    @FXML
    private ChoiceBox c;
    @FXML
    private ChoiceBox c2;
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

    public static void setDateinfo(LocalDate d){
        dateinfo = d;

    }

    public void startMakeBook(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent rootNode = (Parent) loader.load(getClass().getResource("customerMenu.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("makeBooking.fxml"));
        ArrayList<String> services = new ArrayList<>();
        ArrayList<String> employees = new ArrayList<>();
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
                ser.add(s.serviceList.get(i));
                services.add(n);


            }

        }
        c.setItems(FXCollections.observableArrayList(services));
        c.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //this service is saved to the class for use in adding end time of service
                selectedS = ser.get(newValue.intValue());
                if(!bm.checktime(starttime.getText())&&(!selectedS.toString().equals(not.toString())))
                {
                    //do end time
                    updateETime();

                }


            }
        });
        c.setTooltip(new Tooltip("Select the service"));
        e.loadEmployeeInformation();
        for(int i=0;i<e.employeeList.size();i++){
            //makes sure the services of only the current business are displayed
            if(businessID.equals(e.employeeList.get(i).getbId())){
                emp.add(e.employeeList.get(i));
                String n = e.employeeList.get(i).getName();
                System.out.println(n);//checking
                employees.add(n);


            }

        }
        c2.setItems(FXCollections.observableArrayList(employees));
        c2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //this service is saved to the class for use in adding end time of service
                selectedE = emp.get(newValue.intValue());

            }
        });
        c2.setTooltip(new Tooltip("Select the employee"));

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
                    else if(selectedS.toString().equals(not.toString())){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please select Service.");
                        alert.showAndWait();
                    }
                    else {//makes sure null error does not occur
                        updateETime();
                    }
                }
            }
        });
        starttime.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!bm.checktime(starttime.getText())&&(!selectedS.toString().equals(not.toString())))
                {
                    updateETime();
                }
            }
        });

        bdate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(
                    ObservableValue<? extends LocalDate> observableValue,
                    LocalDate oldValue, LocalDate newValue) {

                dateinfo = newValue;//sets the new date value for use
                DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateInfo = dateinfo.format(form);
            }
        });

        checkFile();
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        bdate.setDayCellFactory(dayCellFactory);
        ((AnchorPane) rootNode).getChildren().add(bdate);
        ((AnchorPane) rootNode).getChildren().add(starttime);
        ((AnchorPane) rootNode).getChildren().add(endtime);
        ((AnchorPane) rootNode).getChildren().add(c);
        ((AnchorPane) rootNode).getChildren().add(c2);
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

    }

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
                if(driver.currentBookings.get(i).getEmployeeID()!=null){
                    s+="\nEmployee: " + driver.currentBookings.get(i).getEmployeeID();
                }
                bookings.add(s);
            }

        }
        ObservableList<String> data = FXCollections.observableArrayList(bookings);
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
    public boolean dateCheck(){
        Calendar c = Calendar.getInstance();
        int dayOfYear = c.get(Calendar.DAY_OF_YEAR);
        int Year = c.get(Calendar.YEAR);
        if(dateinfo ==null){return true;}
        if (date.contains(dateinfo.getDayOfWeek())) {
            return true;
        }
        if (dateinfo.getDayOfYear()<dayOfYear||dateinfo.getDayOfYear()>(dayOfYear+30))
        {
            return true;
        }
        if (dateinfo.getYear()!=Year&&dateinfo.getDayOfYear()<=(dayOfYear+30))
        {
            return true;
        }



        return false;
    }
    public void checkBooking(ActionEvent event) throws IOException {//when button clicked
        if(addBooking()){
            //add to file
            Parent home_page = FXMLLoader.load(getClass().getResource("customerMenu.fxml"));
            Scene home_page_scene = new Scene(home_page);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
    }
    public boolean addBooking(){
        if(dateCheck()){//checks correct date entered
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid date entered.");
            alert.showAndWait();
        }
        else if(selectedS.toString().equals(not.toString())){//checks service selected
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select Service.");
            alert.showAndWait();
        }
        else if((startinfo ==null)||bm.checktime(startinfo)){//checks start time choosen
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select Start time.");
            alert.showAndWait();
        }
        else {

            DateFormat time = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat d = new SimpleDateFormat("EEEE");
            DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Date start = Calendar.getInstance().getTime();
            String date = dateinfo.format(form);
            // System.out.println(startinfo + " " + endinfo);
            try {
                start = time.parse(date);
            } catch (ParseException e) {
            }
            String day = d.format(start);
            //gets the time of the service
            System.out.println(day);
            if (bm.UserBooking(businessID, day, startinfo, endinfo)||driver.checkCurrBookings(businessID, start, startinfo, endinfo))
            {
                //checks there are employees working and that the business is open
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("This time is unavailable for booking.");
                alert.showAndWait();
            } else {
                WriteToFile w = new WriteToFile();
                String customername = loginMenu.findCName(customerID);
                String s;
                if(selectedE.toString().equals(not2.toString())){
                    s = businessID + "," + day + "," + date + "," + customername + "," + startinfo + "-" + endinfo + ",";
                    s += selectedS.getName() + "," + customerID+",";}
                else{
                    s = businessID + "," + day + "," + date + "," + customername + "," + startinfo + "-" + endinfo + ",";
                    s += selectedS.getName() + "," + customerID+","+selectedE.geteId();}
                w.WriteToWorkingdayTXT(s, "currentBookings.txt");
                return true;

            }
        }
        return false;
    }
    public void updateETime(){
        if(!bm.checktime(starttime.getText())&&(!selectedS.toString().equals(not.toString())))
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
            startinfo = starttime.getText();
            endinfo = newTime;
        }

    }
}
