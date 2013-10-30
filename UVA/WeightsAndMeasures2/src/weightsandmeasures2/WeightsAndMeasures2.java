/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package weightsandmeasures2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class WeightsAndMeasures2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        ArrayList<Turtle> tl = new ArrayList<Turtle>();
        
        int w, s;
        
        while(in.hasNext()) {
            w = in.nextInt();
          
            if(w == 0)
                break;
            s = in.nextInt();
            
            if(s >= w && w >= 0 && s >= 0) {
                tl.add(new Turtle(w, s));
            }
            
        }
        
        Collections.sort(tl);
        
        int n = tl.size();

        // store total weight best stack of turtles
        int[] b = new int[n+1];
        b[0] = 0;
        for(int i=1; i <= n; i++)
            b[i] = -1;
        
        int curBest = 0, maxBest = 0;
        
        Turtle curtle;
        
        for(int i = 0; i < n; i++) {
            curtle = tl.get(i);
            
            for(int j = maxBest+1; j > 0; j--) {
                if((b[j-1]+curtle.w < b[j] || b[j] == -1) && b[j-1] <= curtle.c) {
                    b[j] = b[j-1]+curtle.w;
                    if(j > maxBest)
                        maxBest = j;
                }
            }
        }
        
//        for(int i=0; i < n; i++) {
//            System.out.println("T: "+tl.get(i).w+" "+tl.get(i).c+" "+b[i]+" "+tw[i]);
//        }
        
        System.out.println(maxBest);
    }
    
    static class Turtle implements Comparable<Turtle> {

        public int w;
        public int s;
        public int c;
        
        public Turtle(int w, int s) {
            this.w = w;
            this.s = s;
            this.c = s - w;
        }

        @Override
        public int compareTo(Turtle o) {
            return this.s - o.s;
        }
        
    }
}
