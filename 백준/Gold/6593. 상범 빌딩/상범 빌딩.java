import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int L;
	static int R;
	static int C;
	static int gx, gy, gz;

	static int dxyz[][] = { { 0, 0, 1 }, { 0, 0, -1 }, { 0, 1, 0 }, { 0, -1, 0 }, { -1, 0, 0 }, { 1, 0, 0 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			L = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();

			if (L == 0 || R == 0 || C == 0)
				break;

			char[][][] map = new char[L][R][C];
			Queue<Node> q = new LinkedList<>();

			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String s = sc.next();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = s.charAt(k);

						if (map[i][j][k] == 'S')
							q.add(new Node(i, j, k, 0));
						
						if (map[i][j][k] == 'E') {
							gx = i;
							gy = j;
							gz = k;
						}
					}
				}
			}
			// End input

			int res = getRes(q, map);

			System.out.println(res == -1 ? "Trapped!" : "Escaped in " + res + " minute(s).");
		}
	}

	static class Node {
		int x, y, z, cnt;

		public Node(int x, int y, int z, int cnt) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
			// TODO Auto-generated constructor stub
		}
	}

	static int getRes(Queue<Node> q, char[][][] map) {
		boolean[][][] visit = new boolean[L][R][C];

		Node nNow = q.peek();
		visit[nNow.x][nNow.y][nNow.z] = true;

		while (!q.isEmpty()) {
			Node n = q.poll();

			if (n.x == gx && n.y == gy && n.z == gz) {
				return n.cnt;
			}

			for (int i = 0; i < dxyz.length; i++) {
				int nx = n.x + dxyz[i][0];
				int ny = n.y + dxyz[i][1];
				int nz = n.z + dxyz[i][2];

				if (nx < 0 || ny < 0 || nz < 0 || nx >= L || ny >= R || nz >= C)
					continue;
				if (map[nx][ny][nz] == '#' || visit[nx][ny][nz])
					continue;
				visit[nx][ny][nz] = true;
				q.add(new Node(nx, ny, nz, n.cnt + 1));

			}

		}

		return -1;

	}
}
