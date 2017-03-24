package user;
/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Employee{
    private String employeeID;
    private String fullName;
    private String taxFileNo;
    private String phoneNo;


    public Employee(String employeeID, String fullName, String taxFileNo, String phoneNo){
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.taxFileNo = taxFileNo;
        this.phoneNo = phoneNo;
    }

    public String toString(){

        return employeeID + ":" +  fullName + ":" + taxFileNo + ":" + phoneNo;
    }

}
