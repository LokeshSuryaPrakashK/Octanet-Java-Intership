import java.sql.*;
import java.util.ArrayList;
public class operations 
{
    long acc_id;
    long acc_to;
    int pin;
    long cash;
    long balance;
    long balance_to;
    static ArrayList<String> al=new ArrayList<>();
    operations(long acc, int p, long a)
    {
        acc_id=acc;
        pin=p;
        cash=a;
    }

    void withdraw()
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
            PreparedStatement smt=con.prepareStatement("select * from available_balance where acc_id=?");
            smt.setLong(1,acc_id);
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                balance=rs.getLong(2);
                if(balance>cash)
                {
                    balance-=cash;
                    try
                    {
                        Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
                        PreparedStatement smt1=con1.prepareStatement("update available_balance set balance=? where acc_id=?");
                        smt1.setLong(1,balance);
                        smt1.setLong(2,acc_id);
                        int i=smt1.executeUpdate();
                        if(i!=0)
                        {
                            System.out.println("Money withdrawed Successfully!\nCollect the cash\n");
                            al.add("Withdraw  : +"+cash);
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Withdraw was unsuccessful");
                    }

                }
                else
                {
                    System.out.println("Insufficient Balance");
                }
            }
            else
            {
                System.out.println("Entered details are incorrect please verify again");
            }
        } 
        catch(Exception e)
        {
            System.out.println("Operation class Connection failed");
        } 
    }

    void deposit()
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
            PreparedStatement smt=con.prepareStatement("select * from available_balance where acc_id=?");
            smt.setLong(1,acc_id);
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                balance=rs.getLong(2);
                balance+=cash;
                try
                {
                    Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
                    PreparedStatement smt1=con1.prepareStatement("update available_balance set balance=? where acc_id=?");
                    smt1.setLong(1,balance);
                    smt1.setLong(2,acc_id);
                    int i=smt1.executeUpdate();
                    if(i!=0)
                    {
                        System.out.println("Money Deposited Successfully!\n");
                        al.add("Deposit   : -"+cash);

                    }
                }
                catch(Exception e)
                {
                    System.out.println("Deposit was unsuccessful");
                }    
            }
            else
            {
                System.out.println("Entered details are incorrect please verify again");
            }
        } 
        catch(Exception e)
        {
            System.out.println("Operation class Connection failed");
        } 
    }

    void transfer(long acc)
    {
        acc_to=acc;
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
            PreparedStatement smt=con.prepareStatement("select * from available_balance where acc_id=?");
            smt.setLong(1,acc_id);
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                balance=rs.getLong(2);
                if(balance>cash)
                {
                    balance=balance-cash;
                    try
                    {
                        Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking_console", "root", "Prakash"); 
                        PreparedStatement smt1=con1.prepareStatement("update available_balance set balance=? where acc_id=?");
                        smt1.setLong(1,balance);
                        smt1.setLong(2,acc_id);
                        int i=smt1.executeUpdate();

                        PreparedStatement smt2=con1.prepareStatement("select * from available_balance where acc_id=?");
                        smt2.setLong(1,acc_to);
                        ResultSet rs2=smt2.executeQuery();
                        if(rs2.next())
                        {
                            balance_to=rs2.getLong(2);
                        }
                        balance_to+=cash;
                        PreparedStatement smt3=con1.prepareStatement("update available_balance set balance=? where acc_id=?");
                        smt3.setLong(1,balance_to);
                        smt3.setLong(2,acc_to);
                        int j=smt3.executeUpdate();

                        if((i!=0) && (j!=0))
                        {
                            System.out.println("Transfer of money was successful\n");
                            al.add("Transfer  : -"+cash);
                        }
                        else
                        {
                            System.out.println("Transfer of money was failed\n");
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Updation in the transaction_from account is unsuccessful");
                    }

                }
                else
                {
                    System.out.println("Insufficient Balance");
                }
            }
            else
            {
                System.out.println("Entered details are incorrect please verify again");
            }
        } 
        catch(Exception e)
        {
            System.out.println("Operation class Connection failed");
        } 
    }

    void history()
    {
        System.out.println("\nTransaction history of account number : "+acc_id);
        for(String i:al)
        {          
            System.out.println(i);
        }
    }

}
