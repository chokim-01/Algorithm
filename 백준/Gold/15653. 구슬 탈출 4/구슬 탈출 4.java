import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int ans;
	static boolean[][][][] visit;

	static class Node {
		int rx, ry, bx, by;

		public Node(int rx, int ry, int bx, int by) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			// TODO Auto-generated constructor stub
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new char[N][M];
		ans = Integer.MAX_VALUE;
		visit = new boolean[N][M][N][M];

		int rx = 0, ry = 0, bx = 0, by = 0;

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}

			}
		}
		bfs(rx, ry, bx, by);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

	static void bfs(int rx, int ry, int bx, int by) {

		Queue<Node> q = new LinkedList<Main.Node>();
		q.add(new Node(rx, ry, bx, by));

		int time = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			while (qsize-- > 0) {
				Node n = q.poll();
				if (map[n.bx][n.by] == 'O')
					continue;
				if (map[n.rx][n.ry] == 'O') {
					ans = time;
					return;
				}
				for (int d = 0; d < 4; d++) {
					int[] red = move(n.rx, n.ry, d, 0);
					int[] blue = move(n.bx, n.by, d, 0);

					rx = red[0];
					ry = red[1];
					bx = blue[0];
					by = blue[1];
					int rcnt = red[2];
					int bcnt = blue[2];

					if (map[bx][by] != 'O' && (rx == bx && ry == by)) {
						if (rcnt > bcnt) {
							rx = rx - dxy[d][0];
							ry = ry - dxy[d][1];
						} else {
							bx = bx - dxy[d][0];
							by = by - dxy[d][1];
						}
					}
					if (visit[rx][ry][bx][by])
						continue;
					visit[rx][ry][bx][by] = true;
					q.add(new Node(rx, ry, bx, by));
				}

			}
			time += 1;
		}

	}

	static int[] move(int x, int y, int d, int cnt) {
		while (true) {
			x = x + dxy[d][0];
			y = y + dxy[d][1];

			if (map[x][y] == '#') {
				x = x - dxy[d][0];
				y = y - dxy[d][1];
				break;
			}
			if (map[x][y] == 'O')
				break;
			cnt++;
		}
		return new int[] { x, y, cnt };
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

}