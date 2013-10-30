
package distinctsubsequences;

import java.io.PrintStream;
import java.util.Scanner;

public class DistinctSubsequences {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);//System.in
        PrintStream ps = System.out; //new PrintStream( new FileOutputStream( "../../solution/candystore.out" ) );
        
        int numTests = sc.nextInt();
        
        for(int test=0; test < numTests; test++) {
            String s1 = sc.next();
            String s2 = sc.next();

            char[] seq = s1.toCharArray();
            char[] sub = s2.toCharArray();
            
            int numSubs = 0;
            
            int[][] ls = new int[sub.length][];

            for(int i=0; i < seq.length; i++) {
                ls[i] = new int[sub.length];
                
                for(int j=0; j < sub.length; j++) {
                    if(seq[i] == sub[j]) {
                        if(j == 0) {
                            ls[i][j]++;
                        }
                        else {
                            for(int k=i-1; k >= 0; k--) {
                                if(ls[k][j-1] > 0) {
                                    ls[i][j] += ls[k][j-1];
                                }
                            }
                        }
                    }
                }
            }
            
            for(int i = 0; i < seq.length; i++) {
                numSubs += ls[i][sub.length-1];
            }
            
//            for(int i=0; i < seq.length; i++) {
//                for(int j=0; j < seq.length; j++) {
//                    System.out.print(ls[j][i]+" ");
//                }
//                System.out.println("");
//            }
//            
            System.out.println(numSubs);
        }
    }
}
