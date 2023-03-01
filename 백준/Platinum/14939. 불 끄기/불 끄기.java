import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int ans;
	static boolean[][] map;
	static boolean[][] mapOrigin;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		input();
		for (int rec = 0; rec < 1024; rec++) {
			init();
			int count = 0;
			// first switch on
			for (int i = 1, num; i <= 10; i++) {
				num = 1024 >> i;
				if ((num & rec) > 0) {
					count++;
					switchOn(0, i - 1);
				}
			}
			dfs(count, 1);
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

	static void dfs(int count, int depth) {
		if (depth == 10) {
			for (boolean b : map[9])
				if (b)
					return;
			ans = ans < count ? ans : count;
			return;
		}
		int c = 0;
		for (int i = 0; i < 10; i++) {
			if (map[depth - 1][i]) {
				c++;
				switchOn(depth, i);
			}
		}
		dfs(count + c, depth + 1);
	}

	static void switchOn(int x, int y) {
		map[x][y] = !map[x][y];
		for (int d = 0, nx, ny; d < 4; d++) {
			nx = x + dxy[d][0];
			ny = y + dxy[d][1];
			if (!mapChk(nx, ny))
				continue;
			map[nx][ny] = !map[nx][ny];
		}

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= 10 || y < 0 || y >= 10)
			return false;
		return true;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		mapOrigin = new boolean[10][10];
		map = new boolean[10][10];

		ans = Integer.MAX_VALUE;

		for (int i = 0; i < 10; i++) {
			String s = br.readLine();
			for (int j = 0; j < 10; j++)
				mapOrigin[i][j] = s.charAt(j) == 'O' ? true : false;
		}
	}

	static void init() {
		for (int i = 0; i < 10; i++)
			map[i] = mapOrigin[i].clone();
	}
}