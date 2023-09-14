import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Main {
	static int N, M;
	static boolean[][] map;
	static int[][][] countMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		makeCountMap();
//		print();
		int ans = findDia();
		System.out.println(ans);

	}

	static int findDia() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int min = Math.min(countMap[i][j][0], countMap[i][j][1]);
				if (min <= ret)
					continue;
				while (min > ret) {
					int X = i + (min - 1);
					int leftY = j - (min - 1);
					int rightY = j + (min - 1);
					min--;
					if (X < 0 || leftY < 0 || rightY < 0 || X >= N || leftY >= M || rightY >= M)
						continue;
					if (countMap[X][rightY][0] < min + 1 || countMap[X][leftY][1] < min + 1)
						continue;
					int min2 = Math.min(countMap[X][leftY][1], countMap[X][rightY][0]);
					int m = Math.min(min + 1, min2);
					if (ret < m)
						ret = m;
				}

			}
		}
		return ret;
	}

	static void makeCountMap() {
		int bef = 0;
		int ni = 0;
		int nj = 0;
		// northeastward
		for (int i = 0; i < N; i++) {
			bef = 0;
			ni = i;
			nj = 0;
			for (;;) {
				if (!mapChk(ni, nj))
					break;
				if (map[ni][nj])
					bef++;
				else
					bef = 0;
				countMap[ni][nj][0] = bef;
				ni -= 1;
				nj += 1;
			}
		}
		for (int j = 1; j < M; j++) {
			bef = 0;
			ni = N - 1;
			nj = j;
			for (;;) {
				if (!mapChk(ni, nj))
					break;
				if (map[ni][nj])
					bef++;
				else
					bef = 0;
				countMap[ni][nj][0] = bef;
				ni -= 1;
				nj += 1;
			}
		}
		// northwestward
		for (int i = 0; i < N; i++) {
			bef = 0;
			ni = i;
			nj = M - 1;
			for (;;) {
				if (!mapChk(ni, nj))
					break;
				if (map[ni][nj])
					bef++;
				else
					bef = 0;
				countMap[ni][nj][1] = bef;
				ni -= 1;
				nj -= 1;
			}
		}
		for (int j = 0; j < M - 1; j++) {
			bef = 0;
			ni = N - 1;
			nj = j;
			for (;;) {
				if (!mapChk(ni, nj))
					break;
				if (map[ni][nj])
					bef++;
				else
					bef = 0;
				countMap[ni][nj][1] = bef;
				ni -= 1;
				nj -= 1;
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0, ni, nj; j < M; j++) {
//				if (!map[i][j])
//					continue;
//				countMap[i][j][0] = countMap[i][j][1] = 1;
//				// left
//				ni = i;
//				nj = j;
//				while (mapChk(ni += 1, nj -= 1))
//					countMap[i][j][0]++;
//				ni = i;
//				nj = j;
//				// right
//				while (mapChk.apply(ni += 1, nj += 1))
//					countMap[i][j][1]++;
//			}
//		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static BiFunction<Integer, Integer, Boolean> mapChk = (a, b) -> (a < 0 || a >= N || b < 0 || b >= M) ? false
			: map[a][b] ? true : false;

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		countMap = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++)
				map[i][j] = s.charAt(j) == '1' ? true : false;
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j])
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(countMap[i][j][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(countMap[i][j][1] + " ");
			}
			System.out.println();
		}
	}
}