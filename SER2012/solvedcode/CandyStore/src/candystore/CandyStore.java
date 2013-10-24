
package candystore;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class CandyStore {

        
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File( "../../judge/candystore.judge"));//new Scanner(System.in); 
        PrintStream ps = System.out; //new PrintStream( new FileOutputStream( "../../solution/candystore.out" ) ); 

        int max, n;
        
        while((n = sc.nextInt()) != 0) {
            max = (int)(sc.nextDouble()*100);
            
            int[] c = new int[n];
            int[] p = new int[n];
            
            int[] b = new int[max+1];
            int curBest, curCal, maxCal=0;
            
            for(int i=0; i < n; i++) {
                c[i] = sc.nextInt();
                p[i] = (int)(sc.nextDouble()*100);
            }
            
            for(int i=0; i <= max; i++) {
                
                for(int j=0; j < n; j++) {
                    curBest = 0;
                    if(p[j] <= i) {
                        curBest = c[j] + b[i-p[j]];
                    }
                    
                    if(curBest > b[i])
                        b[i] = curBest;
                }
            }
            
            System.out.println("best: "+b[max]);
        }
    }
}
