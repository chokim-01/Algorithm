import java.util.Scanner;

class Main {
	static int R, C, T;
	static int[][] map;
	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][][] cleanDxy = { { { 1, 0 }, { -1, 0 } }, { { 0, 1 }, { 0, 1 } }, { { -1, 0 }, { 1, 0 } },
			{ { 0, -1 }, { 0, -1 } } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt(); // T 초가 지난 후 방에 남아있는 미세먼지의 양

		int cleaner = -1;
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1)
					cleaner = i;
			}
		}
		while (T-- > 0) {
			spreadDust();
			clean(cleaner - 2, 1);
			clean(cleaner + 1, 0);
		}

		System.out.println(getAns());
	}

	static int getAns() {
		int count = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (map[i][j] != -1)
					count += map[i][j];
		return count;
	}

	static void clean(int cleaner, int turn) {
		int dir = 0;
		int x = cleaner;
		int y = 0;
		while (true) {
			int nextX = x + cleanDxy[dir][turn][0];
			int nextY = y + cleanDxy[dir][turn][1];
			if (!mapChk(nextX, nextY) || nextX == cleaner + (turn == 0 ? -2 : 2)) {
				nextX = nextX - cleanDxy[dir][turn][0] + cleanDxy[dir + 1][turn][0];
				nextY = nextY - cleanDxy[dir][turn][1] + cleanDxy[dir + 1][turn][1];
				dir += 1;
			}
			if (map[nextX][nextY] == -1) {
				map[x][y] = 0;
				break;
			}
			map[x][y] = map[nextX][nextY];
			x = nextX;
			y = nextY;
		}
	}

	static void spreadDust() {
		int[][] spreadMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0)
					continue;
				if (map[i][j] == -1) {
					spreadMap[i][j] = -1;
					continue;
				}

				int dust = map[i][j];
				// 네 방향 확인하고 더해줌.
				for (int d = 0; d < 4; d++) {
					int nx = i + dxy[d][0];
					int ny = j + dxy[d][1];

					if (!mapChk(nx, ny) || map[nx][ny] == -1)
						continue;
					spreadMap[nx][ny] += map[i][j] / 5;
					dust -= map[i][j] / 5;
				}
				spreadMap[i][j] += dust;
			}
		}
		// 끝났으니 맵 복사
		for (int i = 0; i < R; i++)
			map[i] = spreadMap[i].clone();
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= R || y >= C)
			return false;
		return true;
	}
}