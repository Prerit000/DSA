public class binomialcoeff {
    public static int factorial(int n){
        int fact=1;
        for(int i=1;i<=n;i++){
            fact*=i;
            
        }
        return fact;
    }   
    public static int Bincoeff(int n,int r){
        int nfact=factorial(n);
        int rfact=factorial(r);
        int nmrfact=factorial(n-r);
        int bincoeff=nfact/(rfact*nmrfact);
        return bincoeff;

    }
    public static void main(String args[]){
        System.out.println(Bincoeff(6,2));

    }
    
}
