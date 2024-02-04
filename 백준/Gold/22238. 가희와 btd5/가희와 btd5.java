import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] inp = new int[N][2];
		int[] nums = new int[N + 1];
		BigDecimal gradient;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++)
				inp[i][j] = Integer.parseInt(st.nextToken());
			nums[i + 1] = Integer.parseInt(st.nextToken());
		}
		N++;
		Arrays.sort(nums);
		gradient = getGradient(inp[0][0], inp[0][1]);
		StringBuilder ans = new StringBuilder();
		int damage = 0;
		int bef = N - 1;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			BigDecimal g2 = getGradient(a, b);
			if (gradient.compareTo(g2) != 0 || ((a < 0 && inp[0][0] > 0) || (a > 0 && inp[0][0] < 0))) {
				ans.append(bef).append("\n");
				continue;
			}
			damage = Math.min((int) 1e9, damage + Integer.parseInt(st.nextToken()));

			int l = 0;
			int r = N;
			int ret = 0;
			while (l < r) {
				int mid = (l + r) >> 1;
				if (nums[mid] <= damage) {
					l = mid + 1;
					ret = mid;
				} else
					r = mid;
			}
			ans.append(bef = N - ret - 1).append("\n");
		}
		System.out.println(ans);
	}

	static BigDecimal getGradient(int x, int y) {
		if (x == 0)
			return y > 0 ? BigDecimal.valueOf(2e9) : BigDecimal.valueOf(-2e9);
		if (y == 0)
			return x > 0 ? BigDecimal.valueOf(2e9).add(BigDecimal.valueOf(2))
					: BigDecimal.valueOf(2e9).add(BigDecimal.valueOf(2)).negate();
		return BigDecimal.valueOf(y).divide(BigDecimal.valueOf(x), MathContext.DECIMAL128);
	}
}