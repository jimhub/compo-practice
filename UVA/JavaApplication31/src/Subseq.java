
import java.math.BigInteger;
import java.util.Scanner;

public class Subseq{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int numSeq = s.nextInt();
        String seq;
        String subSeq;
     
        int x;
        BigInteger[] ar;
        for(int a = 0; a < numSeq; a++){
            seq = s.next();
            subSeq = s.next();
            x = subSeq.length();
            ar = new BigInteger[subSeq.length()];
            
            for(int i=0; i < x; i++)
                ar[i] = BigInteger.ZERO;
            
            for(int b = 0; b < seq.length(); b++)
                for(int c = x - 1; c >= 0; c--){
                    if(seq.charAt(b) == subSeq.charAt(c) && c != 0){
                        ar[c] = ar[c].add(ar[c-1]);
                    }
                    else if(c == 0 && seq.charAt(b) == subSeq.charAt(c)){
                        ar[c] = ar[c].add(BigInteger.ONE);
                    }
                }
            System.out.println(ar[subSeq.length() - 1]);
        }
    }
}