import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int W = sc.nextInt();

		boolean[] time = new boolean[T + 1];
		for (int i = 1; i <= T; i++)
			time[i] = sc.nextInt() == 1 ? false : true;

		int[][] dp = new int[T + 1][W + 1];
		for (int i = 1; i <= T; i++) {
			for (int j = 0; j <= W; j++) {
				if (j % 2 == 0) // 1
					dp[i][j] = Math.max(j == 0 ? 0 : dp[i - 1][j - 1], dp[i - 1][j]) + (!time[i] ? 1 : 0);
				else // 2
					dp[i][j] = Math.max(j == 0 ? 0 : dp[i - 1][j - 1], dp[i - 1][j]) + (time[i] ? 1 : 0);

			}
		}
		int max = 0;
		for (int i = 0; i <= W; i++)
			max = max < dp[T][i] ? dp[T][i] : max;
		System.out.println(max);
	}
}