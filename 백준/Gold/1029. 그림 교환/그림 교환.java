import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int[][] map;
	static int[][][] dp;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().chars().map(x -> x - '0').toArray();
		dp = new int[N][1 << N][10];
		System.out.println(dfs(0, 1, 0) + 1);
	}

	static int dfs(int n, int v, int c) {
		if (dp[n][v][c] != 0)
			return dp[n][v][c];
		int l = 0;
		for (int i = 0; i < N; i++) {
			int next = 1 << i;
			if ((next & v) == next || map[n][i] < c)
				continue;
			dp[n][v][c] = Math.max(dp[n][v][c], dfs(i, next | v, map[n][i]) + 1);
		}
		return dp[n][v][c];
	}
}
