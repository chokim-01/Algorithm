import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		int[] dp = new int[N + 201];
		for (int i = 2; i <= N + 200; i++)
			dp[i] = i;

		for (int i = 2; i <= N + 199; i++) {
			dp[i] = Math.min(dp[i + 1] + 1, dp[i]);
			for (int j = i << 1; j <= N + 200; j += i)
				dp[j] = Math.min(dp[j], dp[i] + j / i);
			dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
		}
		System.out.println(dp[N]);
	}
}
