import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int dp[][] = new int[N + 1][10];
		for (int i = 0; i < 10; i++)
			dp[1][i] = 1;

		for (int i = 2; i <= N; i++) { // i : 길이
			for (int j = 0; j < 10; j++) { // j : 0 부터 9까지 숫자 (과거)
				for (int num = 0; num <= j; num++) {// num : 과거의 앞 숫자 (현재, 길이가 -1인 것 중 num 보다 같거나 큰 것)
					dp[i][num] = (dp[i][num] + dp[i - 1][j]) % 10007;
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < 10; i++)
			ans = (ans + dp[N][i]) % 10007;

		System.out.println(ans);
	}
}