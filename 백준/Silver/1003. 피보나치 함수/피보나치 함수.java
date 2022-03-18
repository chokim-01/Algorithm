import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			int[] dp = new int[N + 1];

			dp[0] = 1;
			if (N >= 1)
				dp[1] = 1;
			if (N >= 2)
				dp[2] = 1;

			for (int i = 3; i <= N; i++)
				dp[i] = dp[i - 1] + dp[i - 2];

			System.out.println((N <= 1 ? 1 - N : dp[N - 1]) + " " + (N == 0 ? 0 : dp[N]));
		}

	}

}