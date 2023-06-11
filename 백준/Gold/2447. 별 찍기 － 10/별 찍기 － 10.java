import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static boolean[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		dfs(0, 0, N);
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				ans.append(map[i][j] ? '*' : ' ');
			ans.append("\n");
		}
		System.out.println(ans);
	}

	static void dfs(int w, int h, int n) {
		if (n == 1) {
			map[w][h] = true;
			return;
		}
		// 8분할 가운데 제외 
		int nn = n / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					continue;
				dfs(w + i * nn, h + j * nn, nn);
			}
		}
	}
}