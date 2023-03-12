import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[] dp = new long[N + 1];
		dp[1] = 1;
		if (N >= 2)
			dp[2] = 1;

		for (int i = 3; i <= N; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		System.out.println(dp[N]);
	}
}
/*
 * [1] : 1 1 [2] : 10 [3] : 100 101 [4] : 1000 1010 1001
 * 
 * 
 */
