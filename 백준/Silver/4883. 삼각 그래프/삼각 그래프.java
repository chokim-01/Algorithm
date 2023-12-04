import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder ans = new StringBuilder();
		for (int tc = 1;; tc++) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			int[][] map = new int[N][3];
			for (int i = 0; i < N; i++)
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int[][] dp = new int[N][3];
			dp[0][0] = 10000000;
			dp[0][1] = map[0][1];
			dp[0][2] = map[0][1] + map[0][2];
			for (int i = 1; i < N; i++) {
				dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][0];
				dp[i][1] = Math.min(Math.min(Math.min(dp[i][0], dp[i - 1][1]), dp[i - 1][0]), dp[i - 1][2]) + map[i][1];
				dp[i][2] = Math.min(Math.min(dp[i][1], dp[i - 1][1]), dp[i - 1][2]) + map[i][2];
			}
			ans.append(tc).append(". ").append(dp[N - 1][1]).append("\n");
		}
		System.out.println(ans);
	}
}