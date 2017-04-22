package bookings;

/**
 * Created by xemorth on 4/04/2017.
 */
public class CurrentBookings extends Bookings{

    public CurrentBookings(String business,String dayBooked, String customer, String timeBooked, String serviceBooked, String customerID){
        super(business,dayBooked, customer, timeBooked, serviceBooked, customerID);
    }
}
