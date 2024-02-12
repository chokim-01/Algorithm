import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static boolean[][] map;
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = s.charAt(j) == '1' ? true : false;
		}
		ans = new StringBuilder();
		dfs(0, 0, N);
		System.out.println(ans);
	}

	static void dfs(int x, int y, int c) {
		if (c == 1 || go(x, y, c)) {
			ans.append(tr(x, y));
			return;
		}
		ans.append("(");
		c >>= 1;
		dfs(x, y, c);
		dfs(x, y + c, c);
		dfs(x + c, y, c);
		dfs(x + c, y + c, c);
		ans.append(")");
	}

	static int tr(int x, int y) {
		return map[x][y] ? 1 : 0;
	}

	static boolean go(int x, int y, int c) {
		int lenX = x + c;
		int lenY = y + c;
		boolean bf = map[x][y];
		for (; x < lenX; x++) {
			y = lenY - c;
			for (; y < lenY; y++)
				if (bf != map[x][y])
					return false;
		}
		return true;
	}
}
