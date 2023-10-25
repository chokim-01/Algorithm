import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] v;

	static BiFunction<Integer, Integer, Boolean> chk = (a, b) -> (a < 0 || b < 0 || a >= N || b >= M) ? false : true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][];
		for (int i = 0; i < N; i++)
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		v = new boolean[N][M];
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (v[i][j])
					continue;
				if (bfs(i, j))
					ans++;
			}
		}
		System.out.println(ans);
	}

	static int[][] dxy = { { -1, 0 }, { -1, -1 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	static boolean bfs(int x, int y) {
		boolean ret = true;
		v[x][y] = true;
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d < 8; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!chk.apply(nx, ny))
					continue;
				if (map[nx][ny] > map[x][y])
					ret = false;
				if (map[nx][ny] != map[x][y] || v[nx][ny])
					continue;
				v[nx][ny] = true;
				q.add(new Node(nx, ny));
			}

		}

		return ret;
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}
}