import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		dp = new int[N + 1];
		dp[1] = 1;
		if (N >= 2)
			dp[2] = 2;
		if (N >= 3)
			dp[3] = 3;
		for (int i = 4; i <= N; i++)
			dp[i] = (dp[i - 1] + dp[i - 2])%10007;
		

		System.out.println(dp[N]);

	}
}