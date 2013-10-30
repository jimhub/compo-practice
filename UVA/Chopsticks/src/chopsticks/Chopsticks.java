
package chopsticks;

import java.util.Scanner;

/*
 * I did not solve this.
 * 
 * Ported from http://acm-uva-ufrgs.googlecode.com/svn/trunk/%20acm-uva-ufrgs/Contest%20Volumes/Volume%20CII/10271.cpp
 * to be used as reference.
 * 
 */

public class Chopsticks {
    private static int NCHOPSTICKS = 5000;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int nc; /* number of cases */
        int nge; /* numger of guests */
        int nch; /* number of chopsticks */

        long[] diff = new long[NCHOPSTICKS+1]; /* smallest differences between chopsticks */
        long[] chop = new long[NCHOPSTICKS+1]; /* chopsticks */
        long[] badness = new long[NCHOPSTICKS+1]; /* badness for each pair of chopsticks */

        nc = in.nextInt();

        for(int t=0; t<nc; t++) {
            nge = in.nextInt();
            nch = in.nextInt();
            
            nge += 8;   /* add family members */		
            diff[0] = 0;

            for(int i=0; i<nch; i++) {
                chop[i+1] = in.nextInt();
                diff[i+1] = 0;

                /* calculate badness as (A-B)^2 */
                badness[i+1] = (chop[i+1] - chop[i])*(chop[i+1] - chop[i]);
            }

            /* dynamic programming */
            for(int i=1; i<=nge; i++) {
                for(int j=(nch-3*(nge-i)-1); j>=2*i; j--) {
                    diff[j] = diff[j-2] + badness[j];
                }
                /* find the neighbouring pairs with smallest difference */
                for(int j=(2*i)+1; j<=(nch-3*(nge-i)-1); j++) {
                    if(diff[j-1] < diff[j]) {
                        diff[j] = diff[j-1];
                    }
                }
                /* the third chopstick */
                diff[nch-3*(nge-i)] = diff[nch-3*(nge-i)-1];
            }

            System.out.println(diff[nch]);
        }

    }
}
