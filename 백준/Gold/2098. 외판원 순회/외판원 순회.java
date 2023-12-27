import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ans;
	static int[][] dp;
	static int[][] link;
	static int v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		System.out.println(dfs(0, 1));
	}

	static long dfs(int now, int visit) {
		if (visit == v)
			return link[now][0] == 0 ? Integer.MAX_VALUE : link[now][0];

		if (dp[now][visit] != 0)
			return dp[now][visit];
		dp[now][visit] = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int next = 1 << i;
			if ((visit & next) == next || link[now][i] == 0)
				continue;
			long cost = dfs(i, visit | next) + link[now][i];
			dp[now][visit] = (int) Math.min(dp[now][visit], cost);
		}

		return dp[now][visit];
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		v = (1 << N) - 1;
		StringTokenizer st;
		link = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				link[i][j] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N][1 << N];
	}
}
