import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, R;
	static ArrayList<Node> cows;
	static ArrayList<Node>[][] cant;
	static int[][] map;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			Node n = (Node) o;
			return this.x == n.x && this.y == n.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		bfs();
		System.out.println(getAns());

	}

	static int getAns() {
		int res = 0;
		for (int i = 0; i < K; i++) {
			Node n1 = cows.get(i);
			for (int j = i + 1; j < K; j++) {
				Node n2 = cows.get(j);
				if (map[n1.x][n1.y] != map[n2.x][n2.y])
					res++;
			}
		}

		return res;
	}

	static void bfs() {
		int c = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0)
					continue;
				map[i][j] = c;
				Queue<Node> q = new ArrayDeque<>();
				q.add(new Node(i, j));
				while (!q.isEmpty()) {
					Node n = q.poll();
					for (int d = 0; d < 4; d++) {
						int nx = n.x + dxy[d][0];
						int ny = n.y + dxy[d][1];

						if (!mapChk(nx, ny) || map[nx][ny] != 0)
							continue;
						if (cant[n.x][n.y].contains(new Node(nx, ny)))
							continue;
						map[nx][ny] = c;
						q.add(new Node(nx, ny));
					}
				}
				c++;
			}
		}
//		for (int[] m : map)
//			System.out.println(Arrays.toString(m));
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
		R = Integer.parseInt(st.nextToken());

		cows = new ArrayList<>();
		map = new int[N][N];
		cant = new ArrayList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				cant[i][j] = new ArrayList<>();

		for (int i = 0, a, b, c, d; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			d = Integer.parseInt(st.nextToken()) - 1;
			cant[a][b].add(new Node(c, d));
			cant[c][d].add(new Node(a, b));
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			cows.add(new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
		}
	}
}
