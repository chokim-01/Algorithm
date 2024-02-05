import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static boolean[][] ans;
	static boolean[][][][] stickers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ans = new boolean[N][M];
		stickers = new boolean[K][4][][];
		for (int i = 0, a, b, c; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			stickers[i] = new boolean[4][][];
			stickers[i][0] = new boolean[a][b];

			for (int j = 0; j < a; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < b; k++)
					stickers[i][0][j][k] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			}

			for (int j = 1; j < 4; j++)
				stickers[i][j] = rotate(stickers[i][j - 1]);
		}

		for (int i = 0; i < K; i++)
			go(i);

		int ret = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (ans[i][j])
					ret++;

		System.out.println(ret);
	}

	static void go(int idx) {
		for (int r = 0; r < 4; r++) {
			int sN = stickers[idx][r].length;
			int sM = stickers[idx][r][0].length;

			for (int i = 0; i <= N - sN; i++) {
				outer: for (int j = 0; j <= M - sM; j++) {

					for (int a = 0; a < sN; a++)
						for (int b = 0; b < sM; b++)
							if (stickers[idx][r][a][b] && ans[a + i][b + j])
								continue outer;

					for (int a = 0; a < sN; a++)
						for (int b = 0; b < sM; b++)
							if (stickers[idx][r][a][b])
								ans[a + i][b + j] = true;
					return;
				}
			}

		}
	}

	static boolean[][] rotate(boolean[][] c) {
		int N = c.length;
		int M = c[0].length;

		boolean[][] ret = new boolean[M][N];
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				ret[i][j] = c[N - j - 1][i];

		return ret;
	}
}