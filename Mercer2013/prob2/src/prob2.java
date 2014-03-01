
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class prob2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("prob2_in.txt"));
        //Scanner in = new Scanner(System.in);
        
        PrintStream ps = new PrintStream("out.txt");
        //PrintStream ps = System.out;
        
        int n = in.nextInt();
    
        for(int i=0; i < n; i++) {
            int t = in.nextInt();
            boolean possible = true;
            
            int[] wins = new int[t];
            int[] losses = new int[t];
            
            for(int j=0; j < t; j++) {
                wins[j] = in.nextInt();
                losses[j] = in.nextInt();
            }
            
            while(t > 1 && possible) {
                int winner = 0;
                
                for(int j = 0; j < t; j += 2) {
                    winner = 0;
                    
                    if(losses[j] != 4 && losses[j+1] != 4) {
                        possible = false;
                        break;
                    }
                    
                    if(losses[j] == 4 && wins[j] < 4)
                        winner = 1;
                    else if (losses[j+1] == 4 && wins[j+1] < 4)
                        winner = 0;
                    else {
                        possible = false;
                        break;
                    }
                    
                    wins[j + winner] -= losses[j + (1-winner)];
                    losses[j + winner] -= wins[j + (1-winner)];
                    
                    // advance winner to next round
                    wins[j/2] = wins[j+winner];
                    losses[j/2] = losses[j+winner];
                    
                    //System.out.println((i+1)+": winner: "+wins[j+winner]+" "+losses[j+winner]);
                    
                    // make sure winning team had enough wins to cover losses of other team
                    possible = (wins[j+winner] >= 0 && losses[j+winner] >= 0);
                }
                
                t /= 2;
            }
            
            ps.println("Tournament #"+(i+1)+": "+
                (wins[0] == 0 && losses[0] == 0 && possible ? "Possible" : "Impossible")+"\n");
            
        }
        
    }
    
}
