import java.sql.*;
class tables 
{
    public static void main(String[] args) 
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
            Statement smt=con.createStatement();
            String s1= "create table account(account_number long,user_name varchar(50), pin int)";
            if(smt.executeUpdate(s1)==0)
            {
                System.out.println("Account table created successfully");
                int i=smt.executeUpdate("insert into account values(500101,'Lokesh',1234)");
                if(i==1)
                {
                    System.out.println("Row inserted successfully");
                }
                else
                {
                    System.out.println("Row insertion failed");
                }
            }
            else
            {
                System.out.println("Account Table is not created");
            }
            String s2="create table available_balance(acc_id long,balance long)";
            if(smt.executeUpdate(s2)==0)
            {
                System.out.println("Available Balance table created successfully");
                int i=smt.executeUpdate("insert into available_balance values(500102,5000)");
                if(i==1)
                {
                    System.out.println("Row inserted successfully");
                }
                else
                {
                    System.out.println("Row insertion failed");
                }
            }
            else
            {
                System.out.println("Available Balance Table is not created");
            } 
            con.close();           
        }
        catch(Exception e)
        {
            System.out.println("Error occured while establishing connection\n "+e);
        }
    }
}
