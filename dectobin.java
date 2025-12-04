public class dectobin {
    public static void dectoabin(int n){
        int mynum=n;
        int pow=0;
        int binnum=0;
        while(n>0){
            int rem=n%2;
            binnum=binnum +(rem*(int)Math.powExact(10,pow));
            pow++;
            n=n/2;
        }
        System.out.println("binary of" + mynum +"="+ binnum);
    }
    public static void main(String[] args) {
        dectoabin(7);
        
        
    }
}
