package bookings;

import java.util.logging.Logger;

/**
 * Created by xemorth on 4/04/2017.
 */
public class CurrentBookings extends Bookings{
    private static final Logger LOGGER = Logger.getLogger( CurrentBookings.class.getName() );
    public CurrentBookings(String dayBooked, String customer, String timeBooked, String serviceBooked, String customerID){
        super(dayBooked, customer, timeBooked, serviceBooked, customerID);
    }
}
