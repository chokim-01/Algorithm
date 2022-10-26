import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] diceNum;
	static boolean[][] visit;
	static int rotateDiceSave[][] = { { 2, 0, 5, 3, 4, 1 }, { 1, 5, 0, 3, 4, 2 }, { 4, 1, 2, 0, 5, 3 },
			{ 3, 1, 2, 5, 0, 4 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		init();
		int[] xy = new int[2];

		for (int i = 0; i < 6; i++) {
			for (int j = 0, n; j < 6; j++) {
				n = sc.nextInt();
				map[i][j] = n;
				if (n != 0 && (xy[0] == 0 && xy[1] == 0)) {
					xy[0] = i;
					xy[1] = j;
				}
			}
		}
		diceNum[0] = map[xy[0]][xy[1]];

		dfs(xy[0], xy[1], -1);
		System.out.println(getAns());

	}

	static void init() {
		map = new int[6][6];
		diceNum = new int[6];
		visit = new boolean[6][6];
	}

	static void dfs(int x, int y, int next) {

		// 갈 수 있는 곳 탐색
		for (int d = 0; d < 4; d++) {
			int nx = x + dxy[d][0];
			int ny = y + dxy[d][1];

			if (!nextChk(nx, ny) || visit[nx][ny])
				continue;
			visit[nx][ny] = true;
			// 주사위 회전 후 이동
			rotateDice(d);
			diceNum[0] = map[nx][ny];
			dfs(nx, ny, d);

		}
		if (next != -1) // 처음이 아니면 주사위 회전
			rotateDice(reverseDir(next));
	}

	static void rotateDice(int d) {
		int[] tmp = new int[6];
		for (int i = 0; i < 6; i++)
			tmp[rotateDiceSave[d][i]] = diceNum[i];
		diceNum = tmp.clone();
	}

	static int reverseDir(int d) {
		if (d == 0)
			return 1;
		if (d == 1)
			return 0;
		if (d == 2)
			return 3;
		else
			return 2;
	}

	static boolean nextChk(int x, int y) {
		if (x < 0 || y < 0 || x >= 6 || y >= 6 || map[x][y] == 0)
			return false;
		return true;
	}

	static int getAns() {
		int num1Loc = 0;
		for (int i = 0; i < 6; i++) {
			if (diceNum[i] == 1) {
				num1Loc = i;
				break;
			}
		}
		for (int i = 0; i < 6; i++)
			if (diceNum[i] == 0)
				return 0;

		int ansL[] = { 5, 2, 1, 4, 3, 0 };

		return diceNum[ansL[num1Loc]];
	}
}