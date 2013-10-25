/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuttingsticks;

import java.io.PrintStream;
import java.util.Scanner;

public class CuttingSticks {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //new File( "candystore.in" ) );
        PrintStream ps = System.out; //new PrintStream( new FileOutputStream( "candystore.out" ) ); 

        int l;

        while ((l = sc.nextInt()) != 0) {
            int n = sc.nextInt();

            int[] m = new int[n + 2];

            m[0] = 0;
            m[n + 1] = l;

            for (int i = 1; i < n + 1; i++) {
                m[i] = sc.nextInt();
            }

            int[][] costs = new int[n + 2][];
            for (int i = 0; i < n + 2; i++) {
                costs[i] = new int[n + 2];
            }

            for (int i = 0; i < n; i++) {
                costs[i][i + 2] = m[i + 2] - m[i];
            }

            int curMin;
            int bestMin = 0;

            for (int i = 3; i <= n + 1; i++) {
                for (int j = 0; j <= n + 1 - i; j++) {

                    for (int k = 1; k <= i - 1; k++) {
                        curMin = m[j + i] - m[j];
                        curMin += costs[j][j + k];
                        curMin += costs[j + k][j + i];
                        
                        if (k == 1 || curMin < bestMin) {
                            bestMin = curMin;
                        }
                    }
                    costs[j][j+i] = bestMin;
                }
            }
            
            System.out.println(costs[0][n + 1]);
        }
    }
}

