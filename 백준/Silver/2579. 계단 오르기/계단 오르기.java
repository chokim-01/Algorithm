import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] step = new int[N + 1];
		int[][] dp = new int[N + 1][2];

		for (int i = 1; i <= N; i++)
			step[i] = sc.nextInt();

		// 0은 한칸, 1은 두칸
		dp[1][0] = step[1];
		if (N >= 2) {
			dp[2][0] = step[1] + step[2];
			dp[2][1] = step[2];
		}

		for (int i = 3; i <= N; i++) {
			dp[i][0] = dp[i - 1][1] + step[i];
			dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + step[i];
		}

		System.out.println(Math.max(dp[N][0], dp[N][1]));

	}
}