
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class prob3 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("prob3_in.txt"));
        //Scanner in = new Scanner(System.in);
        
        //PrintStream ps = new PrintStream("out.txt");
        PrintStream ps = System.out;
        
        int w, h;
        int county = 1;
        
        while((w = in.nextInt()) != 0) {
            h = in.nextInt();
            
            int[][] b = new int[h+2][w+2];
            int[][] b2 = new int[h+2][w+2];
            int[][] swapper;
            int[][] f = new int[h+2][w+2];
            
            int tc = h*w;
            
            for(int i=1; i <= h; i++) {
                String s = in.next();
                
                for(int j=0; j < s.length(); j++)
                    b[i][j+1] = (int)s.charAt(j);
            }
            
            int zapped = -1;
            
            while(zapped != 0) {
                zapped = 0;
                
                for(int i = 1; i <= h; i++) {
                    for(int j = 1; j <= w; j++) {
                        b2[i][j] = b[i][j];
                        
                        int curNum = b[i][j];
                        
                        if(b[i][j] != 0) {
                            int chainSize = floodFill(b, i, j, curNum, -1);
                            
                            if(chainSize > 3) {
                                zapped += chainSize;
                                
                                floodFill(b, i, j, -1, 0);
                                shiftDown(b2, i, j);
                            }
                            else {
                                floodFill(b, i, j, -1, curNum);
                            }
                        }
                        else {
                            shiftDown(b2, i, j);
                        }
                    }
                }
                
                swapper = b;
                b = b2;
                b2 = swapper;
                
                tc -= zapped;
            }
            
            ps.println((county++)+": "+tc);
        }
    }
    
    public static void shiftDown(int[][] arr, int row, int col) {
        while(row-- > 0) {
            arr[row+1][col] = arr[row][col];
        }
    }
    
    public static int floodFill(int[][] arr, int x, int y, int replace, int fillNum) {
        if(arr[x][y] != replace)
            return 0;
        int fillCount = 1;
        
        arr[x][y] = fillNum;
        
        fillCount += floodFill(arr, x-1, y, replace, fillNum);
        fillCount += floodFill(arr, x, y+1, replace, fillNum);
        fillCount += floodFill(arr, x+1, y, replace, fillNum);
        fillCount += floodFill(arr, x, y-1, replace, fillNum);
        
        return fillCount;
    }
    
}
