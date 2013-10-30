
package weightsandmeasures;

import java.util.Scanner;


public class WeightsAndMeasures {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int[] weights = new int[5607];
        int[] strengths = new int[5607];
        
        int i = s.nextInt();
        int j = s.nextInt();
        
        int counter = 0;
        while(i > 0 && j > 0){
            weights[counter] = i;
            strengths[counter++] = i;
            
            i = s.nextInt();
            j = s.nextInt();
        }
        
        int max = 0;
        int min = weights[0];
        int[] turtleStack = new int[5607];
        for(int k = 0; i < counter; i++){
            if((strengths[k] - weights[k]) > max){
                max = k;
                turtleStack[0] = strengths[k] - weights[k];
            }
            if(weights[k] < min){
                min = weights[k];
            }
        }
        
        
    }
}
