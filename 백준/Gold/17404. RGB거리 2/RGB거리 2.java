import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N + 1][3];

		for (int i = 1; i <= N; i++)
			for (int j = 0; j < 3; j++)
				map[i][j] = sc.nextInt();
		dp = new int[3];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= 2; i++) {
			Arrays.fill(dp, 1000000);
			dp[i] = map[1][i];
			for (int j = 2; j <= N; j++) {
				int a = Math.min(dp[1], dp[2]);
				int b = Math.min(dp[0], dp[2]);
				int c = Math.min(dp[1], dp[0]);

				dp[0] = a + map[j][0];
				dp[1] = b + map[j][1];
				dp[2] = c + map[j][2];
				if (j == N)
					dp[i] = Integer.MAX_VALUE;
			}
			for (int d : dp)
				min = Math.min(min, d);
		}
		System.out.println(min);
	}
}