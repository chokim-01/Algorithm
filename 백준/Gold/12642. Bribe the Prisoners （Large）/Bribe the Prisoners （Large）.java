import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int p, q;
	static int[] releases;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("Case #" + tc + ":" + " ");

			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			releases = new int[q];
			memo = new int[q][q];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < q; i++)
				releases[i] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < q; i++)
				Arrays.fill(memo[i], Integer.MAX_VALUE);
			int r = dfs(1, p, 0, q - 1);

			sb.append(r).append("\n");
		}
		System.out.println(sb);
	}

	// a,b : P : 1 to p
	// c,d : Q : 0 to q-1
	static int dfs(int a, int b, int c, int d) {
		if (a < 1 || b > p || a > b)
			return 0;
		if (c < 0 || d >= q || c > d)
			return 0;
		int ret = memo[c][d];
		if (ret != Integer.MAX_VALUE)
			return ret;
        // exist, left + right
		for (int i = c; i <= d; i++)
			ret = Math.min(ret, dfs(a, releases[i] - 1, c, i - 1) + dfs(releases[i] + 1, b, i + 1, d) + b - a);

		return memo[c][d] = ret;
	}
}