import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[][] dp = new long[N + 1][M + 1];
		int[][] arr = new int[N + 1][];
		for (int i = 1; i <= N; i++)
			arr[i] = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int k = 1; k <= M; k++)
			for (int i = 1; i <= N; i++) {
				dp[i][k] = arr[i][k];
				for (int j = 1; j <= N; j++)
					dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + arr[i][k] / (i == j ? 2 : 1));
			}
		long max = 0;
		for (int i = 1; i <= N; i++)
			max = Math.max(dp[i][M], max);

		System.out.println(max);
	}
}
//10
// 