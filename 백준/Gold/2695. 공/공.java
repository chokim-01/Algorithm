import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		int[][] dp = new int[51][1001];
		for (int i = 1; i <= 50; i++)
			for (int j = 1; j <= 1000; j++)
				dp[i][j] = j;
		for (int i = 0; i <= 50; i++)
			dp[i][0] = 200000000;

		for (int i = 2; i <= 50; i++)
			for (int j = 2; j <= 1000; j++)
				for (int k = 1; k < j; k++)
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][j - k], dp[i - 1][k]) + 1);

		StringBuilder ans = new StringBuilder();
		while (P-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int B = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ans.append(dp[B][M] - 1).append("\n");
		}
		System.out.println(ans);
	}
}