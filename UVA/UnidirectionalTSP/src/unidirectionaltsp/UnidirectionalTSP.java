
package unidirectionaltsp;

import java.util.Scanner;

public class UnidirectionalTSP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()) {
            long minPathSum = Long.MAX_VALUE;
            
            int rows = in.nextInt();
//            if(rows == 0)
//                break;
            int cols = in.nextInt();
            
            long[][] m = new long[rows][cols];
            long[][] w = new long[rows][cols];
            
            for(int i=0; i < rows; i++) {
                //m[i] = new int[cols];
                //w[i] = new int[cols];
                
                for(int j=0; j < cols; j++) {
                    m[i][j] = in.nextInt();
                    
                    if(cols == 1) {
                        if(m[i][j] < minPathSum) {
                            minPathSum = m[i][j];
                        }
                    }
                    
                    if(j == cols-1) {
                        w[i][j] = m[i][j];
                    }
                    else {
                        w[i][j] = Long.MAX_VALUE;
                    }
                }
            }
            
            // proper k
            int pk;

            for(int i = cols-2; i >= 0; i--) {
                for(int j = 0; j < rows; j++) {
                    for(int k = j-1; k <= j+1; k++) {
                        pk = (k % rows + rows) % rows;
                        
                        if(w[j][i] > (m[j][i]+w[pk][i+1])) {
                            w[j][i] = m[j][i]+w[pk][i+1];
                            
                            if(i == 0) {
                                if(w[j][i] < minPathSum)
                                    minPathSum = w[j][i];
                            }
                        }
                    }
                }
            }
            
            for(int i = 0; i < rows; i++) {
                if(w[i][0] == minPathSum) {
                    printPath(w, i, cols);
                    break;
                }
            }
            
            System.out.println(minPathSum);
        }
    }
    
    public static void printPath(long[][] w, int r, int cols) {
        System.out.print((r+1));
        
        int rl, rr, minR;
        long minW;
        
        for(int c = 1; c < cols; c++) {
            rl = ((r-1) % w.length + w.length) % w.length;
            rr = (r+1) % w.length;
            
            minR = Integer.MAX_VALUE;
            minW = Math.min(w[rl][c], Math.min(w[r][c], w[rr][c]));
            
            if(w[rl][c] == minW)
                minR = rl;
            if(w[r][c] == minW && r < minR)
                minR = r;
            if(w[rr][c] == minW && rr < minR)
                minR = rr;
            
            System.out.print(" "+(minR+1));
            r = minR;
        }
        System.out.println();
    }
}
