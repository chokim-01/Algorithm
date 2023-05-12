import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static final int mod = 2 * 3 * 5 * 7;
	static final int[] modArr = { 2, 3, 5, 7 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("Case #").append(tc).append(": ");

			int[] numbers = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

			int N = numbers.length;
			int M = mod;
			long[][] dp = new long[N + 1][M];

			dp[0][0] = 1;
			for (int i = 0; i < N; i++) {
				int number = 0;

				// make number
				for (int j = i; j < N; j++) {
					number *= 10;
					number = (number + numbers[j]) % mod;
					for (int n = 0; n < mod; n++) {
						if(dp[i][n] == 0)
							continue;
						// +
						int next = (n + number) % mod;
						dp[j + 1][next] += dp[i][n];
						if (i == 0)
							continue;
						// -
						next = (n - number + mod) % mod;
						dp[j + 1][next] += dp[i][n];
					}
				}
			}
			long ans = 0;
			outer: for (int n = 0; n < mod; n++) {
				for (int m : modArr) {
					if (n % m == 0) {
						ans += dp[N][n];
						continue outer;
					}
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}