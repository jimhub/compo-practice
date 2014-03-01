
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class prob12 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("prob12_in.txt"));
        //Scanner in = new Scanner(System.in);
        
        //PrintStream ps = new PrintStream("out.txt");
        PrintStream ps = System.out;
        
        
        int cc = in.nextInt();
        
        for(int c = 0; c < cc; c++) {
            int n = in.nextInt();
            
            Location[] l = new Location[n];
            
            for(int i = 0; i < n; i++)
                l[i] = new Location(in.nextInt());
            
            // number of paths
            int numPaths = in.nextInt();
            // kill end line after int
            in.nextLine();
            
            for(int i=0; i < numPaths; i++) {
                String p = in.nextLine();
                // trim parens
                p = p.substring(1, p.length()-1);
                
                String[] v = p.split(",");
                
                int from = Integer.valueOf(v[0].trim());
                int to = Integer.valueOf(v[1].trim());
                
                l[from-1].addNeiighghhghebebbeor(l[to-1]);
                l[to-1].addNeiighghhghebebbeor(l[from-1]);
            }
            
            // dp to get path costs
            int[][] d = new int[n][n];
            int[][] path = new int[n][n];
            
            for(int i=0; i < n; i++) {
                for(int j=0; j < n; j++) {
                    d[i][j] = Integer.MAX_VALUE;
                    path[i][j] = Integer.MAX_VALUE;
                    
                    if(i == j) {
                        d[i][j] = 0;
                    }
                    else if(l[i].neighbors.containsKey(l[j])) {
                        d[i][j] = l[i].neighbors.get(l[j]);
                    }
                }
            }
            
            int added;
            
            for(int k = 0; k < n; k++) {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        added = d[i][k] + d[k][j];
                        
                        if(added < 0)
                            added = Integer.MAX_VALUE;
                        
                        if(added < d[i][j]) {
                            d[i][j] = added;
                            path[i][j] = k;
                        }
                    }
                }
            }
            
            int pathTotal = 0;

            int numPoints = in.nextInt();

            int start = in.nextInt();

            for(int j = 0; j < numPoints-1; j++) {
                int end = in.nextInt();
                pathTotal += d[start-1][end-1];
                start = end;
            }
            
            ps.println("The least amount of work on trip "+(c+1)+" is: "+pathTotal);
        }
    }
    
    static class Location {
        public int elevation;
        
        public HashMap<Location, Integer> neighbors = new HashMap<>();
        
        public Location(int elevation) {
            this.elevation = elevation;
        }
        
        public void addNeiighghhghebebbeor(Location neigh) {
            neighbors.put(neigh, dist(neigh));
        }
        
        private int dist(Location l) {
            return Math.abs(elevation - l.elevation);
        }
        
        public int pathTo(Location n) {
            int pathCost = 0;
            
            if(this == n)
                return 0;
            
            
            return pathCost;
        }
    }
}
