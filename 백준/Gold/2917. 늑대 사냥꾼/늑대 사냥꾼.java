import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int s[], e[];
	static char[][] map;
	static int[][] tMap;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
//	static BiFunction<Integer, Integer, Boolean> chk = (x, y) -> x >= 0 && y >= 0 && x < N && y < M;

	static class Node implements Comparable<Node> {
		int x, y, save;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.save = Integer.MAX_VALUE;
		}

		public Node(int x, int y, int s) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.save = s;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return o.save - this.save;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		treeMapSet();
		System.out.println(solve());
	}

	static void treeMapSet() {
		Queue<Node> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			Arrays.fill(tMap[i], Integer.MAX_VALUE);
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '+') {
					tMap[i][j] = 0;
					q.add(new Node(i, j, 0));
				} else if (map[i][j] == 'V')
					s = new int[] { i, j };
				else if (map[i][j] == 'J')
					e = new int[] { i, j };
			}
		}
		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int i = 0, nx, ny; i < dxy.length; i++) {
				nx = n.x + dxy[i][0];
				ny = n.y + dxy[i][1];
				if (!chk(nx, ny))
					continue;
				if (tMap[nx][ny] != Integer.MAX_VALUE)
					continue;
				tMap[nx][ny] = n.save + 1;
				q.add(new Node(nx, ny, tMap[nx][ny]));
			}
		}
	}

	static boolean chk(int x, int y) {
		if (x >= N || y >= M || x < 0 || y < 0)
			return false;
		return true;
	}

	static int solve() {
		PriorityQueue<Node> q = new PriorityQueue<>();

		q.add(new Node(s[0], s[1], tMap[s[0]][s[1]]));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (tMap[n.x][n.y] == -1)
				continue;
			if (n.x == e[0] && n.y == e[1])
				return n.save;
			tMap[n.x][n.y] = -1;
			for (int i = 0, nx, ny; i < dxy.length; i++) {
				nx = n.x + dxy[i][0];
				ny = n.y + dxy[i][1];
				if (!chk(nx, ny) || tMap[nx][ny] == -1)
					continue;
				q.add(new Node(nx, ny, Math.min(n.save, tMap[nx][ny])));
			}
		}
		return 0;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		tMap = new int[N][M];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
	}

}