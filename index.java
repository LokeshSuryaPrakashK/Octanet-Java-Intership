import java.util.*;
class Main
{
    public static void main(String args[])
    {
        long acc_id;
        int pin;
        boolean exit_loop=false;
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your Acoount ID");
        acc_id=sc.nextLong();
        System.out.println("Enter your PIN");
        pin=sc.nextInt();
        validate v=new validate(acc_id,pin);
        if(v.validate_data(acc_id, pin))
        {
            System.out.println("\nLogin was successful");
            while(!exit_loop)
            {
                System.out.println("\nPlease select any one option \n 1.Withdraw\n 2.Deposit\n 3.Transfer\n 4.Transaction History\n 5.Quit");
                int op=sc.nextInt();
                switch(op)
                {
                    case 1:
                        System.out.println("Please enter the amount :");
                        long a=sc.nextLong();
                        operations o1=new operations(acc_id,pin,a);
                        o1.withdraw();
                        exit_loop=false;
                        break;
                    case 2:
                        System.out.println("Please enter the amount :");
                        long am=sc.nextLong();
                        operations o=new operations(acc_id,pin,am);
                        o.deposit();
                        exit_loop=false;
                        break;
                    case 3:
                        System.out.println("Enter the account number of customer who you want to transfer");
                        long acc_to=sc.nextLong();
                        System.out.println("Enter the amount");
                        long amo=sc.nextLong();
                        operations o2=new operations(acc_id,pin,amo);
                        o2.transfer(acc_to);
                        exit_loop=false;
                        break;
                    case 4:
                        System.out.println("Transaction History:");
                        operations o4=new operations(acc_id, pin, a=0);
                        o4.history();
                        exit_loop=false;
                        break;
                    case 5:
                        exit_loop=true;
                        break;
                }
            }
        }
        else
        {
            System.out.println("Entered details were not found");
        }
        sc.close();
    }
}