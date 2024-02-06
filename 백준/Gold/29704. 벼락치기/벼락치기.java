import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		int sum = 0;
		int[][] arr = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
			sum += arr[i][1];
		}

		int[][] dp = new int[N + 1][T + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= T; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= arr[i][0])
					dp[i][j] = Math.max(dp[i - 1][j - arr[i][0]] + arr[i][1], dp[i][j]);
			}
		}
		System.out.println(sum - dp[N][T]);
	}
}