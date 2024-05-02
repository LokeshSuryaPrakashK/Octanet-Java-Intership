import java.sql.*;
public class validate 
{
    long acc_id;
    int pin;
    validate(long a,int p)
    {
        acc_id=a;
        pin=p;
    }
    boolean validate_data(long acc_id,int pin)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
            PreparedStatement smt=con.prepareStatement("select * from account where account_number=? and pin=?");
            smt.setLong(1,acc_id);
            smt.setInt(2,pin);
            ResultSet rs=smt.executeQuery();
            return(rs.next());
        } 
        catch(Exception e)
        {
            System.out.println("Validate class Connection failed");
        } 
        return false;  
    }
}


