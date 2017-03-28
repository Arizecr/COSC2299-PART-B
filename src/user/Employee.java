package user;
/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Employee {
    private String employeeID;
    private String businessId;
    private String fullName;
    private String taxFileNo;
    private String phoneNo;


    public Employee(String businessId, String employeeID, String fullName, String taxFileNo, String phoneNo){
        this.employeeID = employeeID;
        this.businessId = businessId;
        this.fullName = fullName;
        this.taxFileNo = taxFileNo;
        this.phoneNo = phoneNo;
    }

    public String toString(){

        return businessId+":"+employeeID + ":" +  fullName + ":" + taxFileNo + ":" + phoneNo;
    }
    public String geteId(){

        return employeeID;
    }

    public String getbId(){

        return businessId;
    }

}
