import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		boolean[][][] dp = new boolean[N << 1][N + 1][N];
		// i를 i개 봤을 때
		// j를 j개 뽑았을 때
		// k를 나머지 k로 만들 수 있는지
		dp[0][0][0] = true;
		for (int i = 1; i < N << 1; i++) {
			// dp[nums[i-1]]
			dp[i][0][0] = true;
			for (int j = 1; j <= N; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = dp[i - 1][j][k] | dp[i - 1][j - 1][(k - nums[i - 1] + N) % N];
				}
			}
		}
		StringBuilder ans = new StringBuilder();

		int s = N << 1;
		int d = N;
		int k = 0;

		while (--s > 0 && d != 0) {
			int num = nums[s - 1];

			if (dp[s - 1][d - 1][(k - num + N) % N]) {
				ans.append(num).append(" ");
				d -= 1;
				k = (k - num + N) % N;
			}
		}
		System.out.println(ans);
	}
}
