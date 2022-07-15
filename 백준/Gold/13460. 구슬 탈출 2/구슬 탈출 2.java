import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {

	static int N;
	static int M;
	static char[][] map;
	static boolean[][][][] visit;
	static int goalx;
	static int goaly;
	static int res;

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}
	/*
	 * 빨간 공의 위치와 파란 공의 위치를 기억.
	 * 
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new char[N][M];
		visit = new boolean[N][M][N][M];

		Queue<Node> qblue = new LinkedList<>();
		Queue<Node> qred = new LinkedList<>();
		res = Integer.MAX_VALUE;
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;

		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'B') {
					qblue.add(new Node(i, j));
					a = i;
					b = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'R') {
					qred.add(new Node(i, j));
					map[i][j] = '.';
					c = i;
					d = j;
				}
				if (map[i][j] == 'O') {
					goalx = i;
					goaly = j;
				}

			}
		}
		// Inp
		visit[a][b][c][d] = true;
		dfs(qblue, qred, 0);

		System.out.println(res == Integer.MAX_VALUE ? -1 : res);
	}

	static void dfs(Queue<Node> qblue, Queue<Node> qred, int cnt) {
		// 10번 이상 흔들었으면 꽝
		Node nb = qblue.poll();
		Node nr = qred.poll();

		if (cnt > 10)
			return;
		// 파란 공 빠졌으면 꽝
		if (goalx == nb.x && goaly == nb.y) {
			return;
		}

		if (goalx == nr.x && goaly == nr.y) {
			// 정답
			if (res > cnt)
				res = cnt;
			return;
		}

		// 일단 모두 민 후 겹친 것에서 아래에 있던애를 한칸 아래로
		// 이런 식으로.

		for (int i = 0; i < dxy.length; i++) {
			int bx = nb.x;
			int by = nb.y;
			int rx = nr.x;
			int ry = nr.y;

			// 빨간공
			while (true) {
				if (map[rx][ry] == '#') {
					rx -= dxy[i][0];
					ry -= dxy[i][1];
					break;
				}
				// 골을 찾으면 좌표에 대기
				if (goalx == rx && goaly == ry) {
					break;
				}

				rx += dxy[i][0];
				ry += dxy[i][1];
			}

			// 파란공
			while (true) {
				if (map[bx][by] == '#') {
					bx -= dxy[i][0];
					by -= dxy[i][1];
					break;
				}
				// 골을 찾으면 좌표에 대기
				if (goalx == bx && goaly == by) {
					break;
				}

				bx += dxy[i][0];
				by += dxy[i][1];
			}

			if (bx == rx && by == ry && (map[bx][by] != 'O')) {

				// 상
				if (i == 0)
					if (nb.x > nr.x)
						bx += 1;
					else
						rx += 1;

				if (i == 1)
					if (nb.x > nr.x)
						rx -= 1;
					else
						bx -= 1;

				if (i == 2)
					if (nb.y > nr.y)
						by += 1;
					else
						ry += 1;

				if (i == 3)
					if (nb.y > nr.y)
						ry -= 1;
					else
						by -= 1;
			}

			if (!visit[bx][by][rx][ry]) {
				visit[bx][by][rx][ry] = true;
				qblue.add(new Node(bx, by));
				qred.add(new Node(rx, ry));
				dfs(qblue, qred, cnt + 1);
				visit[bx][by][rx][ry] = false;

			}

		}

		// 빨간 공 빠졌으면 카운트 갱신

	}

}
