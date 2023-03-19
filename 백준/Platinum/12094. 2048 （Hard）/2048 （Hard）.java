import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int ans;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] map = new int[N][N];

		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0, n; j < N; j++) {
				n = sc.nextInt();
				map[i][j] = n;
				max = max < n ? n : max;
			}
		}

		dfs(0, map, -1, max);

		System.out.println(ans);
	}

	static void dfs(int time, int[][] map, int jump, int max) {

		if (time >= 10) {
			ans = Math.max(ans, max);
			return;
		}
		if (!calcMax(time, max))
			return;

		for (int d = 0; d < 4; d++) {
			if (jump != 1 && d == jump)
				continue;
			int[][] mapClone = new int[N][N];
			for (int i = 0; i < N; i++)
				mapClone[i] = map[i].clone();
			int[] arr = moveMap(d, mapClone, max);
			dfs(time + 1, mapClone, arr[1], arr[0]);

		}

	}

	static boolean calcMax(int time, int max) {
		time = 10 - time;
		if (max << time <= ans)
			return false;
		return true;
	}

	static int[] moveMap(int d, int[][] map, int max) {
		boolean[][] visit = new boolean[N][N];
		boolean jumpFlag = false;
		if (d == 2) {
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					if (map[i][j] == 0)
						continue;
					int k = j;
					int cnt = map[i][j];
					boolean flag = false;
					while (--k >= 0) {
						if (map[i][k] == 0) {
							flag = true;
							continue;
						}

						if (map[i][k] == cnt && !visit[i][k]) {
							visit[i][k] = true;
							map[i][k] *= 2;
							max = max < map[i][k] ? map[i][k] : max;
							map[i][j] = 0;
							jumpFlag = true;
							flag = false;
						}
						if (visit[i][k])
							break;
						if (map[i][k] != cnt)
							break;
					}
					if (flag) {
						map[i][k + 1] = cnt;
						map[i][j] = 0;
					}
				}
			}
		}
		if (d == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 2; j >= 0; j--) {
					if (map[i][j] == 0)
						continue;
					int k = j;
					int cnt = map[i][j];
					boolean flag = false;
					while (++k < N) {
						if (map[i][k] == 0) {
							flag = true;
							continue;
						}
						if (map[i][k] == cnt && !visit[i][k]) {
							visit[i][k] = true;
							map[i][k] *= 2;
							max = max < map[i][k] ? map[i][k] : max;
							map[i][j] = 0;
							jumpFlag = true;
							flag = false;
						}
						if (visit[i][k])
							break;
						if (map[i][k] != cnt)
							break;
					}
					if (flag) {
						map[i][j] = 0;
						map[i][k - 1] = cnt;
					}
				}
			}
		}
		if (d == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					if (map[j][i] == 0)
						continue;
					int k = j;
					int cnt = map[j][i];
					boolean flag = false;
					while (--k >= 0) {
						if (map[k][i] == 0) {
							flag = true;
							continue;
						}

						if (map[k][i] == cnt && !visit[k][i]) {
							visit[k][i] = true;
							map[k][i] *= 2;
							max = max < map[k][i] ? map[k][i] : max;
							map[j][i] = 0;
							jumpFlag = true;
							flag = false;
						}
						if (visit[k][i])
							break;
						if (map[k][i] != cnt)
							break;

					}
					if (flag) {
						map[k + 1][i] = cnt;
						map[j][i] = 0;
					}
				}
			}
		}
		if (d == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 2; j >= 0; j--) {
					if (map[j][i] == 0)
						continue;
					int k = j;
					int cnt = map[j][i];
					boolean flag = false;
					while (++k < N) {
						if (map[k][i] == 0) {
							flag = true;
							continue;
						}
						if (map[k][i] == cnt && !visit[k][i]) {
							visit[k][i] = true;
							map[k][i] *= 2;
							max = max < map[k][i] ? map[k][i] : max;
							map[j][i] = 0;
							jumpFlag = true;
							flag = false;
						}
						if (visit[k][i])
							break;
						if (map[k][i] != cnt)
							break;
					}
					if (flag) {
						map[k - 1][i] = cnt;
						map[j][i] = 0;
					}
				}
			}
		}
		if (!jumpFlag)
			return new int[] { max, d };

		return new int[] { max, -1 };
	}
}