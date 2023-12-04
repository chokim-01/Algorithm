import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static boolean[][] map;
	static ArrayList<Node>[][] light;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return this.x + "," + this.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		go();
		System.out.println(getAns());
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int getAns() {
		int r = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j])
					r++;
		return r;
	}

	static void go() {
		boolean[][] v = new boolean[N][N];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0));

		for (Node n : light[0][0]) {
			if (map[n.x][n.y])
				continue;
			map[n.x][n.y] = true;
		}
		v[0][0] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			boolean flag = true;
			for (int s = 0; s < size; s++) {
				Node n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];
					if (!mapChk(nx, ny) || v[nx][ny] || !map[nx][ny])
						continue;
					v[nx][ny] = true;
					flag = false;
					q.add(new Node(nx, ny));
					for (Node next : light[nx][ny]) {
						if (!map[next.x][next.y]) {
							map[next.x][next.y] = true;
						}
					}
				}
				q.add(n);
			}
			if (flag)
				break;
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[N][N];
		light = new ArrayList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				light[i][j] = new ArrayList<>();
		map[0][0] = true;
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			light[a][b].add(new Node(c, d));
		}
	}
}