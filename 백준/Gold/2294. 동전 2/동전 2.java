import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		int[] dp = new int[K + 1];
		// 값 중 최소 max 값
		Arrays.fill(dp, K + 1);
		dp[0] = 0;

		// coins
		int[] coin = new int[N + 1];
		for (int i = 1; i <= N; i++)
			coin[i] = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) { // K까지
				if (j < coin[i])
					continue;
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
			}
		}
		if (dp[K] == K + 1)
			System.out.println(-1);
		else
			System.out.println(dp[K]);
	}
}