import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			// TODO Auto-generated constructor stub
		}
	}

	// out : 2 cheese : 1 else : 0
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();
		int res = -1;
		Queue<Node> q = new LinkedList<>();

		Queue<Node> air = new LinkedList<>();

		while (true) {
			res += 1;
			if (cheeseCnt(map))
				break;
			air.add(new Node(0, 0));
			map[0][0] = 2;

			boolean[][] visit = new boolean[N][M];

			// air check
			while (!air.isEmpty()) {
				Node n = air.poll();
				for (int i = 0; i < 4; i++) {
					int nx = n.x + dxy[i][0];
					int ny = n.y + dxy[i][1];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (visit[nx][ny] || map[nx][ny] == 1)
						continue;
					visit[nx][ny] = true;
					map[nx][ny] = 2;
					air.add(new Node(nx, ny));
				}

			}

			// cheese Chk
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						if (cheeseCheck(map, i, j)) {
							q.add(new Node(i, j));
						}
					}
				}

			// cheese del
			while (!q.isEmpty()) {
				Node n = q.poll();
				map[n.x][n.y] = 0;
			}

		}

		System.out.println(res);

	}

	static boolean cheeseCheck(int[][] map, int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dxy[i][0];
			int ny = y + dxy[i][1];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (map[nx][ny] == 2)
				cnt += 1;

			if (cnt >= 2)
				return true;

		}

		return false;
	}

	static int dxy[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean cheeseCnt(int[][] map) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == 1)
					return false;
		return true;
	}

}
