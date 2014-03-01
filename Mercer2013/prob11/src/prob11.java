

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;

public class prob11 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("prob6_in.txt"));
        //Scanner in = new Scanner(System.in);
        
        PrintStream ps = new PrintStream("out.txt");
        //PrintStream ps = System.out;
        
        String s;
        
        HashSet<String> morse = new HashSet();
        
        morse.add("-...");
        morse.add("-.-.");
        morse.add("..-.");
        morse.add("....");
        morse.add(".---");
        morse.add(".-..");
        morse.add(".--.");
        morse.add("--.-");
        morse.add("...-");
        morse.add("-..-");
        morse.add("-.--");
        morse.add("--..");
        
        while(!(s = in.nextLine()).equals("#")) {

            int[] b = new int[s.length()+4];
            b[0] = 1;
            b[1] = 1;
            b[2] = 2;
            b[3] = 4;
            
            if(s.length() == 0) {
                ps.println("0");
            }
            else if(s.length() < 4) {
                ps.println(b[s.length()]);
            }
            else {
                for(int i = 4; i <= s.length(); i++) {
                    int curSum = 0;
                    for(int j = 1; j <= 4; j++) {

                        if(j < 4 || morse.contains(s.substring(i-4, i))) {
                            curSum += b[i-j];
                        }
                    }
                    b[i] = curSum;
                }
                
                ps.println(b[s.length()]);
            }
            
        }
            
    }
}
