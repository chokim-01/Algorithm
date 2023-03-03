import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int count = Integer.MAX_VALUE;
	static int[][] dxy = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) {

		// TODO Auto-generated constructor stub

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int map[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = sc.next();

			for (int j = 0; j < M; j++)
				map[i][j] = s.charAt(j) - '0';

		}

		// dfs(map, new boolean[N][M], 0, 0, 1);

		bfs(map, new boolean[N][M]);
		System.out.println(count);
	}

	static class Status {
		int x, y, cnt;

		public Status(int r, int c, int cnt) {
			this.x = r;
			this.y = c;
			this.cnt = cnt;
		}
	}

	static void bfs(int[][] map, boolean[][] bool) {
		Queue<Status> q = new LinkedList<>();
		q.add(new Status(0, 0, 1));

		bool[0][0] = true;

		while (!q.isEmpty()) {
			Status qv = q.poll();
			if (qv.x == map.length - 1 && qv.y == map[0].length - 1) {
				count = qv.cnt;
				return;
			}

			for (int i = 0; i < dxy.length; i++) {

				int nx = qv.x + dxy[i][0];
				int ny = qv.y + dxy[i][1];
				if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length)
					continue;
				if (!bool[nx][ny] && map[nx][ny] == 1) {

					q.offer(new Status(nx, ny, qv.cnt + 1));
					bool[nx][ny] = true;
				}

			}
		}

	}

	static void dfs(int[][] map, boolean[][] bool, int x, int y, int cnt) {//// 방문 체크배열, 지도 정보, 현재 방문할 좌표, 지금까지
																			//// 카운트
		if (x == map.length - 1 && y == map[0].length - 1)
			if (count > cnt)
				count = cnt;

		if (!bool[x][y]) {

			for (int i = 0; i < dxy.length; i++) {

				if (x + dxy[i][0] >= 0 && x + dxy[i][0] < map.length && y + dxy[i][1] >= 0
						&& y + dxy[i][1] < map[0].length) {
					if (map[x + dxy[i][0]][y + dxy[i][1]] == 1 && !bool[x][y]) {

						bool[x][y] = true;
						dfs(map, bool, x + dxy[i][0], y + dxy[i][1], cnt + 1);
						bool[x][y] = false;

					}

				}

			}

		}
	}

}
