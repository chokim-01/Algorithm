import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//0144
public class Main {
	static int N, M;
	static int[][][] fishMap;
	static int[][][] fishEggMap;

	static int[][] fishDead;
	static int[][] duckDxy = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int[][] fishDxy = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = 4;
		int n = sc.nextInt();
		M = sc.nextInt();

		fishMap = new int[N][N][8];
		fishEggMap = new int[N][N][8];
		fishDead = new int[N][N];

		for (int i = 0; i < n; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			fishMap[a][b][c] += 1;
		}
		int duckX = sc.nextInt() - 1;
		int duckY = sc.nextInt() - 1;
		while (M-- > 0) {
			// 1. 알을 낳음
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					fishEggMap[i][j] = fishMap[i][j].clone();

			int toFish[][][] = new int[N][N][8];
			// 2. 물고기 이동 -> 새로운 맵
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < 8; k++) {
						if (fishMap[i][j][k] == 0)
							continue;
						boolean flag = false;
						for (int d = 0; d < 8; d++) {
							int ndir = (k - d + 8) % 8;
							int nx = i + fishDxy[ndir][0];
							int ny = j + fishDxy[ndir][1];

							if (!mapChk(nx, ny))
								continue;
							if (nx == duckX && ny == duckY)
								continue;
							if (fishDead[nx][ny] != 0)
								continue;
							toFish[nx][ny][ndir] += fishMap[i][j][k];
							flag = true;
							break;
						}

						if (!flag)
							toFish[i][j][k] += fishMap[i][j][k];
					}
				}
			}
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					fishMap[i][j] = toFish[i][j].clone();
			// 3. 오리 이동
			delCnt = -1;
			toDuckX = 0;
			toDuckY = 0;
			delFishLoc = new LinkedList<Node>();

			duckMove(duckX, duckY, 0, new LinkedList<Node>());
			duckX = toDuckX;
			duckY = toDuckY;

			// 잡아먹은 것 지움
			// 시체가 생김
			
			for (Node delN : delFishLoc) {
				boolean flag = false;
				for (int i = 0; i < 8; i++) {
					if (fishMap[delN.x][delN.y][i] != 0)
						flag = true;
					fishMap[delN.x][delN.y][i] = 0;
				}
				if (flag)
					fishDead[delN.x][delN.y] = 3;
			}

			// 시체 년수가 줄어듬
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					fishDead[i][j] = fishDead[i][j] == 0 ? 0 : fishDead[i][j] - 1;

			// 알이 부화함
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					for (int k = 0; k < 8; k++) {
						if (fishEggMap[i][j][k] == 0)
							continue;
						fishMap[i][j][k] += fishEggMap[i][j][k];
					}
		}
		int ans = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < 8; k++)
					ans += fishMap[i][j][k];
		System.out.println(ans);

	}

	static void print() {
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int a = 0;
				for (int k = 0; k < 8; k++)
					a += fishMap[i][j][k];
				System.out.print(a + " ");
			}
			System.out.println();
		}
		System.out.println(tmp);
	}

	static int delCnt;
	static int toDuckX;
	static int toDuckY;
	static LinkedList<Node> delFishLoc;

	static void duckMove(int x, int y, int depth, List<Node> find) {
		if (depth == 3) {
			int cnt = 0;
			boolean[][] visit = new boolean[4][4];
			for (int i = 0; i < find.size(); i++) {
				Node n = find.get(i);
				if (visit[n.x][n.y])
					continue;
				visit[n.x][n.y] = true;
				for (int a : fishMap[n.x][n.y]) 
					cnt += a;
			}
			if (delCnt < cnt) {
				delCnt = cnt;
				toDuckX = x;
				toDuckY = y;

				delFishLoc.clear();
				for (int i = 0; i < find.size(); i++) {
					Node n = find.get(i);
					delFishLoc.add(new Node(n.x, n.y));
				}
			}
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + duckDxy[d][0];
			int ny = y + duckDxy[d][1];

			if (!mapChk(nx, ny))
				continue;

			find.add(new Node(nx, ny));
			duckMove(nx, ny, depth + 1, find);
			find.remove(find.size() - 1);
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}

}