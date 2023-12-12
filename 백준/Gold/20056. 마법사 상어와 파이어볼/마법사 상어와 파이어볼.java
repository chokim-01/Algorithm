import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//1132
public class Main {
	static int N, M, K;
	static int ans;
	static int dxy[][] = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int[][] order;
	static List<Node>[][] map; // 존재하는 파이어볼 . 중간에 추가.

	static List<Node> fList;

	static class Node {
		int x, y, m, d, s;

		public Node(int x, int y, int m, int d, int s) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.d = d;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", m=" + m + ", d=" + d + ", s=" + s + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		ans = 0;
		map = new List[N][N];
		fList = new LinkedList<Main.Node>();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = new LinkedList<Main.Node>();

		// x, y, 질량, 방향, 속력
		for (int i = 0, a, b, c, d, e; i < M; i++) {

			a = sc.nextInt() - 1;
			b = sc.nextInt() - 1;
			c = sc.nextInt();
			e = sc.nextInt();
			d = sc.nextInt();
			map[a][b].add(new Node(a, b, c, d, e));
			// 파이어볼 리스트
		}

		while (--K >= 0) {
			// 맵에 파이어볼 리스트에 세팅.
			fList.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() == 0)
						continue;
					for (int a = 0; a < map[i][j].size(); a++)
						fList.add(map[i][j].get(a));
				}
			}
			// 맵 초기화
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					map[i][j] = new LinkedList<Node>();
			// 1. 파이어볼 자신의 방향으로 속력만큼 이동
			for (int i = 0; i < fList.size(); i++) {
				Node n = fList.get(i);
				int nx = (n.x + n.s * dxy[n.d][0] + 251 * N) % N;
				int ny = (n.y + n.s * dxy[n.d][1] + 251 * N) % N;

				map[nx][ny].add(new Node(nx, ny, n.m, n.d, n.s));
			}
			// 하나로 합침
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() < 2)
						continue;
					int size = map[i][j].size();
					int mass = 0;
					int speed = 0;
					int dir = map[i][j].get(0).d % 2;
					boolean flag = false;
					for (int a = 0; a < size; a++) {
						Node n = map[i][j].get(a);
						mass += n.m;
						speed += n.s;
						// true : 1,3,5,7
						if (dir != n.d % 2)
							flag = true;
					}
					speed /= map[i][j].size();
					mass /= 5;

					map[i][j].clear();
					if (mass == 0)
						continue;

					for (int di = 0; di < 4; di++) {
						int n = di * 2 + 1;
						if (!flag)
							n = di * 2;
						map[i][j].add(new Node(i, j, mass, n, speed));
					}
				}
			}
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < map[i][j].size(); k++)
					ans += map[i][j].get(k).m;

		System.out.println(ans);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
}