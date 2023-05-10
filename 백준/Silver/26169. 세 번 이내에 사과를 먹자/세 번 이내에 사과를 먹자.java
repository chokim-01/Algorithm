import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N = 5;
	static int[][] map;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		ans = 0;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		map[x][y] = -1;
		System.out.println(dfs(x, y, 0, 0) ? 1 : 0);
	}

	static boolean dfs(int x, int y, int apple, int cnt) {
		if (apple >= 2)
			return true;

		if (cnt >= 3)
			return false;
		for (int d = 0; d < 4; d++) {
			int nx = x + dxy[d][0];
			int ny = y + dxy[d][1];
			if (!mapChk(nx, ny))
				continue;
			int tmp = map[nx][ny];
			map[nx][ny] = -1;
			if (dfs(nx, ny, apple + tmp, cnt + 1))
				return true;
			map[nx][ny] = tmp;
		}
		return false;
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N || map[x][y] == -1)
			return false;
		return true;
	}
}