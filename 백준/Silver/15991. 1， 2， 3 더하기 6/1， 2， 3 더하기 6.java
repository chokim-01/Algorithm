import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static long[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		dp = new long[100001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 2;
		dp[4] = 3;
		dp[5] = 3;
		dp[6] = 6;

		for (int i = 7; i <= 100000; i++)
			dp[i] = (dp[i - 2] + dp[i - 4] + dp[i - 6]) % 1000000009;

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			System.out.println(dp[N]);
		}
	}
}