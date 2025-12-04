public class bintodec {
    public static void bintoadec(int binnum){
        int mynum=binnum;
        int pow=0;
        int decnum=0;
        while(binnum>0){
            int lastd=binnum%10;
            decnum=decnum+(lastd*(int)Math.powExact(2, pow));
            pow++;
            binnum=binnum/10;
        }
        System.out.println("decimal of"+ mynum +"="+ decnum);

    }
    public static void main(String[] args) {
        bintoadec(101);
    }
}
