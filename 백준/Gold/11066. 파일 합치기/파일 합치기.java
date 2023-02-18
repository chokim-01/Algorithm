import java.util.*;

public class Main {
    static int[] a = new int[501];
    static int[][] d = new int[501][501];

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextInt();
                Arrays.fill(d[i], -1);
            }
            System.out.println(solve(1, n));
        }
    }

    public static int solve(int i, int j) {
        if (i == j) return 0;
        if (d[i][j] != -1) return d[i][j];
        d[i][j] = Integer.MAX_VALUE;
        int sum = 0;
        for (int k = i; k <= j; k++) sum += a[k];
        for (int k = i; k <= j-1; k++) {
            int temp = solve(i, k) + solve(k+1, j) + sum;
            if (d[i][j] > temp) d[i][j] = temp;
        }
        return d[i][j];
    }
}