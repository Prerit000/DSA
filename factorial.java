import java.util.Scanner;

public class factorial {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("enter any positive integer");
        int num=sc.nextInt();
        int fact=1;
        for(int i=1;i<=num;i+=1){
            fact*=i;
        }
        System.out.println("factorial is "+fact);


    }
}
