import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int[][] memo;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		memo = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j);
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				ans = ans > memo[i][j] ? ans : memo[i][j];
		System.out.println(ans);
	}

	static int dfs(int x, int y) {
		if(memo[x][y] != 0)
			return memo[x][y];
		memo[x][y] = 1;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dxy[d][0];
			int ny = y + dxy[d][1];
			if (!mapChk(nx, ny) || map[x][y] >= map[nx][ny])
				continue;
			memo[x][y] = Math.max(memo[x][y], dfs(nx, ny)+1);
		}
		return memo[x][y];
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
}