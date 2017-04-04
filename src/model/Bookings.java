package model;

/**
 * Created by xemorth on 4/04/2017.
 */
public class Bookings {

    private String dayBooked;
    private String customer;
    private String timeBooked;
    private String serviceBooked;

    public Bookings(String dayBooked, String customer, String timeBooked, String serviceBooked) {
        this.dayBooked = dayBooked;
        this.customer = customer;
        this.timeBooked = timeBooked;
        this.serviceBooked = serviceBooked;
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



}
