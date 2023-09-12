import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] fMap;
	static BiFunction<Integer, Integer, Boolean> mapChk = (a, b) -> (a >= 0 && a < N && b < M && b >= 0) ? true : false;

	static class Node {
		int x, y, c;

		public Node(int x, int y, int c) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		fMap = new int[N][M];
		Queue<Node> human = new ArrayDeque<>();
		Queue<Node> fire = new ArrayDeque<>();
		for (int i = 0; i < N; i++)
			Arrays.fill(fMap[i], Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++)
				if (map[i][j] == 'J')
					human.add(new Node(i, j, 0));
				else if (map[i][j] == 'F')
					fire.add(new Node(i, j, 0));
				else if (map[i][j] == '#')
					fMap[i][j] = Integer.MAX_VALUE;
		}

		spreadF(fire, fMap);
		int ans = spreadH(human);
		System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);

	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int spreadH(Queue<Node> q) {
		Node n = q.peek();
		boolean[][] v = new boolean[N][M];
		v[n.x][n.y] = true;
		while (!q.isEmpty()) {
			n = q.poll();
			if (map[n.x][n.y] != 'F' && map[n.x][n.y] != '#')
				if (n.x == 0 || n.y == 0 || n.x == N - 1 || n.y == M - 1)
					return n.c + 1;
			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk.apply(nx, ny) || map[nx][ny] == '#')
					continue;
				if (v[nx][ny] || fMap[nx][ny] <= n.c + 1)
					continue;
				v[nx][ny] = true;
				q.add(new Node(nx, ny, n.c + 1));
			}
		}
		return -1;
	}

	static void spreadF(Queue<Node> q, int[][] m) {
		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk.apply(nx, ny) || map[nx][ny] == '#')
					continue;
				if (m[nx][ny] <= n.c + 1)
					continue;
				m[nx][ny] = n.c + 1;
				q.add(new Node(nx, ny, n.c + 1));
			}
		}
	}

}