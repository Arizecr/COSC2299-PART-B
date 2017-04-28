package bookings;

/**
 * Created by xemorth on 4/04/2017.
 */
public class PastBookings extends Bookings{
    private String cancelled;
    public PastBookings(String business,String dayBooked,String date, String customer, String timeBooked, String serviceBooked, String cancelled, String customerID){
        super(business,dayBooked,date, customer, timeBooked, serviceBooked, customerID);
        this.cancelled = cancelled;
    }

    public String getCancelledStatus() {

        return cancelled;
    }

}
