import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static double[][] dp;
	static double[][] link;
	static int v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		System.out.println(dfs(0, 1));
	}

	static double dfs(int now, int visit) {
		if (visit == v)
			return link[now][0] == 0 ? Integer.MAX_VALUE : link[now][0];

		if (dp[now][visit] != 0)
			return dp[now][visit];
		dp[now][visit] = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int next = 1 << i;
			if ((visit & next) == next || link[now][i] == 0)
				continue;
			double cost = dfs(i, visit | next) + link[now][i];
			dp[now][visit] = Math.min(dp[now][visit], cost);
		}

		return dp[now][visit];
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		v = (1 << N) - 1;
		StringTokenizer st;
		int[][] map = new int[N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}
		link = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				link[i][j] = Math.sqrt((map[i][0] - map[j][0]) * (map[i][0] - map[j][0])
						+ (map[i][1] - map[j][1]) * (map[i][1] - map[j][1]));
			}
		}
		dp = new double[N][1 << N];
	}
}
