import java.util.*;
public class incometax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int income= sc.nextInt();
        int tax;
        if (income<500000) {
            System.out.println("tax is 0");
            
        }else if (income>=500000 && income<=1000000) {
            tax=(int) (0.2f*income);
            System.out.println("your tax is:"+tax);
            
        }else{
            tax=(int) (0.3f*income);
            System.out.println("your tax is:"+tax);
        }
    }
    
}
