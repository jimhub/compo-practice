
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class prob1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("prob1_in.txt"));
        java.io.PrintStream ps = new java.io.PrintStream("out.txt");

        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            int left, mid, right, num = in.nextInt();
            boolean impossible = true;

            for (int j = (int) Math.sqrt(num); j > 1; j--) {
                if (num % j == 0) {
                    mid = num / j;
                    left = mid - (j - 1);
                    right = mid + (j - 1);

                    if (isOdd(left) && isOdd(right)) {
                        ps.println(num + ": [" + left + ", " + right + "]");
                        impossible = false;
                    }
                }
            }

            if (impossible) {
                ps.println(num + ": impossible");
            }
        }
    }

    public static boolean isOdd(int n) {
        return n % 2 == 1;
    }
}
