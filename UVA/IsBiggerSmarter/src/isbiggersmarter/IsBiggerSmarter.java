
package isbiggersmarter;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * This code sorts the elephants by weight, low-to-high, then performs
 * a longest decreasing subsequence check on the IQs.
 * 
 * Uses DP algorithm:
 * 
 *  LS(i) = 1 + max{ LS(j) where IQ[i] < IQ[j] and W[i] > W[j] for 0 <= j < i }
 * 
 */
public class IsBiggerSmarter {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File( "test-small.txt"));//new File( "test-small.txt")System.in
        PrintStream ps = System.out; //new PrintStream( new FileOutputStream( "../../solution/candystore.out" ) );
        
        ArrayList<Elephant> e = new ArrayList<Elephant>();
        
        int w, iq, count = 0;
        
        // Capture all elephants from input data
        while(sc.hasNext()) {
            count++;
            w = sc.nextInt();
            iq = sc.nextInt();
            
            e.add(new Elephant(w, iq, count));
        }
        

        Collections.sort(e);
        
//        for(Elephant el : e)
//            System.out.println(el.oi+" "+el.w+" "+el.iq);
                
        
        int n = e.size();
        
        // LS(i) = 1 + max{ LS(j) where IQ[i] < IQ[j] and W[i] > W[j] for 0 <= j < i }
        
        int[] ls = new int[n];
        
        // Stores the backtrace of the indices for the longest sequence.
        int[] next = new int[n+1];
        
        int maxOfMax=0, maxIndex=0;
        Elephant curEle;
        
        for(int i = 0; i < n; i++) {
            curEle = e.get(i);
            ls[i] = 1;
            next[i] = -1;
            
            for(int j = 0; j < i; j++) {
                if(curEle.iq < e.get(j).iq && curEle.w > e.get(j).w) {
                    if(ls[i] < ls[j]+1) {
                        ls[i] = ls[j]+1;
                        next[i] = j;
                    }
                }
            }

            if(ls[i] > maxOfMax) {
                maxOfMax = ls[i];
                maxIndex = i;
            }
        }
        
        System.out.println(maxOfMax);
        
        ArrayList<Integer> answerList = new ArrayList<Integer>();
        
        // Getting the length of the longest sequence was the easy part.
        // Getting the actual items from the longest was a pain in the ass.
        while(next[maxIndex] != -1) {
            answerList.add(e.get(maxIndex).oi);
            maxIndex = next[maxIndex];
        }
        answerList.add(e.get(maxIndex).oi);
        
        for(int i = answerList.size()-1; i >=0; i--)
            System.out.println(answerList.get(i));
        
    }
    
    static class Elephant implements Comparable<Elephant> {
        public int w;   // weight
        public int iq;  // IQ
        public int oi;  // original index in input
        
        public Elephant(int w, int iq, int oi) {
            this.w = w;
            this.iq = iq;
            this.oi = oi;
        }

        @Override
        public int compareTo(Elephant o) {
            // Order by weight ascending first, then IQ descending
            if(this.w == o.w) {
                return o.iq - this.iq;
            }
            
            return this.w - o.w;
        }
    }
}
