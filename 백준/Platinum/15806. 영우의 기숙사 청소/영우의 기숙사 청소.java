import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, T;

	static boolean[][][] map;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	static int[][] dxy = { { -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new boolean[N][N][2];
		Queue<Node> q = new ArrayDeque<>();
		for (int i = 0, a, b; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			q.add(new Node(a, b));
		}

		int time = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			if (++time > T)
				break;
			while (size-- > 0) {
				Node n = q.poll();
				for (int[] d : dxy) {
					int nx = n.x + d[0];
					int ny = n.y + d[1];
					if (!mapChk(nx, ny))
						continue;
					if (map[nx][ny][time % 2])
						continue;
					map[nx][ny][time % 2] = true;
					q.add(new Node(nx, ny));
				}
			}
		}
		// security
		for (int j = 0, a, b; j < K; j++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;

			if (map[a][b][T % 2]) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
}