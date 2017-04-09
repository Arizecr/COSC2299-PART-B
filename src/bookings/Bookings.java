package bookings;

import java.util.logging.Logger;

/**
 * Created by xemorth on 4/04/2017.
 */
public abstract class Bookings {
    private static final Logger LOGGER = Logger.getLogger( Bookings.class.getName() );
    private String dayBooked;
    private String customer;
    private String timeBooked;
    private String serviceBooked;
    private String customerID;

    public Bookings(String dayBooked, String customer, String timeBooked, String serviceBooked, String customerID) {
        this.dayBooked = dayBooked;
        this.customer = customer;
        this.timeBooked = timeBooked;
        this.serviceBooked = serviceBooked;
        this.customerID = customerID;
    }


    public String toString() {

        return dayBooked + ":" + customer + ":" + timeBooked + ":" + serviceBooked;
    }

    public String getDayBooked() {

        return dayBooked;
    }

    public String getCustomer() {

        return customer;
    }

    public String getTimeBooked() {

        return timeBooked;
    }

    public String getServiceBooked() {

        return serviceBooked;
    }

    public String getCustomerID() {

        return customerID;
    }



}
