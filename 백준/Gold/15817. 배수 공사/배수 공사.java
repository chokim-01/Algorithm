import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
			arr[i][1] *= arr[i][0];
		}
		int[] dp = new int[X + 1];
		dp[0] = 1;
		for (int n = 1; n <= N; n++)
			for (int i = X; i > 0; i--)
				for (int p = arr[n][0]; p <= arr[n][1] && i - p >= 0; p += arr[n][0])
					dp[i] += dp[i - p];

		System.out.println(dp[X]);
	}
}