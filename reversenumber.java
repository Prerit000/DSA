public class reversenumber {
    public static void main(String[] args) {
        /* 
        //printing reverse of a number
        int n=10899;
        while(n>0){
            int lastdigit=n%10;
            System.out.println(lastdigit);
            n=n/10;
        }
            */
        // reverse the given number
        int n=10899;
        int rev=0;
        while(n>0){
            int lastdigit=n%10;
            rev=(rev*10)+lastdigit;
            n=n/10;
        }
        System.out.println(rev);    

    }
}
