import java.io.IOException;
import java.util.Scanner;

public class Main {
	static long[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		dp = new long[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= 1000000; i++)
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			System.out.println(dp[N]);
		}

	}
}