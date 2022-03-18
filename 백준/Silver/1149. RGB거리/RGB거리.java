import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N+1][3];

		for (int i = 1; i <= N; i++)
			for (int j = 0; j < 3; j++)
				map[i][j] = sc.nextInt();
		dp = map[1];

		for (int i = 2; i <= N; i++) {
			int a = Math.min(dp[1] , dp[2]);
			int b = Math.min(dp[0] , dp[2]);
			int c = Math.min(dp[1] , dp[0]);
			
			dp[0] = a + map[i][0];
			dp[1] = b + map[i][1];
			dp[2] = c + map[i][2];
		}
		System.out.println(Math.min(Math.min(dp[0] , dp[1]) , dp[2]));
	}
}