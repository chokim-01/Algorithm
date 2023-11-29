import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Node {
		int x, y, beforex, beforey;

		public Node(int x, int y, int beforex, int beforey) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.beforex = beforex;
			this.beforey = beforey;
		}
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N;
	static int M;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		char[][] map = new char[N][M];

		// Set map
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < s.length(); j++)
				map[i][j] = s.charAt(j);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (queStart(map, i, j)) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}

	static boolean queStart(char[][] map, int x, int y) {
		boolean[][] tmp_map = new boolean[N][M];

		boolean flag = false;
		tmp_map[x][y] = true;

		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(x, y, x, y));

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int i = 0; i < dxy.length; i++) {
				int nx = n.x + dxy[i][0];
				int ny = n.y + dxy[i][1];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (map[n.x][n.y] == map[nx][ny]) {
					// match before again
					if (nx == n.beforex && ny == n.beforey)
						continue;
					if (tmp_map[nx][ny])
						return true;
					else
						q.add(new Node(nx, ny, n.x, n.y));
				}
			}
		}
		return flag;
	}
}