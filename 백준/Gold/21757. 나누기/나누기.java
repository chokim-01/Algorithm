import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		sum = new long[N + 1];

		st = new StringTokenizer(br.readLine());
		sum[1] = Long.parseLong(st.nextToken());
		for (int i = 2; i <= N; i++)
			sum[i] = Long.parseLong(st.nextToken()) + sum[i - 1];
		long ans = 0;
		if (sum[N] % 4 == 0) {

			if (sum[N] == 0) {
				long count = -1;
				for (long s : sum)
					count = count + (s == 0 ? 1 : 0);
				if (count >= 4)
					// 4 부터 1,4,10,20,....
					ans = (count - 1) * (count - 2) * (count - 3) / 6;
			} else {
				// 반드시 네개
				ans = dynamicP();
			}
		}
		System.out.println(ans);
	}

	static long dynamicP() {
		long[] dp = new long[N + 1];
		dp[0] = 1;
		for (int i = 1; i <= N; i++) {
			int mok = (int) (sum[i] / (sum[N] / 4));
			int nameoji = (int) (sum[i] % (sum[N] / 4));
			if (mok < 1 || mok > 4)
				continue;
			if (nameoji != 0)
				continue;

			dp[mok] += dp[mok - 1];
		}
		return dp[4];
	}
}