package Menu;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class CustomerMenu {
    Login login = new Login();

    public void printMenu(){

        System.out.println("\n+----------------------------------+");
        System.out.println("|           Actor               |");
        System.out.println("|              Menu                |");
        System.out.println("+----------------------------------+");

        System.out.println("\n1. Book appointment");
        System.out.println("2. View Booking(s)");


        for(int i=0; i < login.customerList.size() ;i++){
            System.out.println(login.customerList.get(i).getUsername());

        }





    }
}
