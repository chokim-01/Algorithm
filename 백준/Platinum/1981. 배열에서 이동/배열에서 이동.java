import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static BiFunction<Integer, Integer, Boolean> chk = (x, y) -> x >= 0 && x < N && y >= 0 && y < N;

	static class Node {
		int x, y, min, max;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.min = map[x][y];
			this.max = map[x][y];
		}

		public Node(int x, int y, int mi, int ma) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.min = mi;
			this.max = ma;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(binSearch());
	}

	static int binSearch() {
		int ret = 0;
		int l = 0;
		int r = 200;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (bfs(mid)) {
				ret = mid;
				r = mid - 1;
			} else
				l = mid + 1;
		}
		return ret;
	}

	static boolean bfs(int dist) {
		Queue<Node> q = new ArrayDeque<>();
		// 3 2
		// 0 3 1 4 2 5
		// 0 5 1 6 2 7

		for (int s = 0; s <= dist; s++) {
			if (map[0][0] - s < 0)
				continue;
			int start = map[0][0] - s;
			int end = map[0][0] - s + dist;

			visit = new boolean[N][N];
			visit[0][0] = true;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (map[i][j] < start || map[i][j] > end)
						visit[i][j] = true;

			q.add(new Node(0, 0));
			while (!q.isEmpty()) {
				Node n = q.poll();

				for (int[] d : dxy) {
					int nx = n.x + d[0];
					int ny = n.y + d[1];
					if (!chk.apply(nx, ny) || visit[nx][ny])
						continue;
					int max = n.max < map[nx][ny] ? map[nx][ny] : n.max;
					int min = n.min < map[nx][ny] ? n.min : map[nx][ny];
					if (max - min > dist)
						continue;
					visit[nx][ny] = true;
					if (nx == N - 1 && ny == N - 1)
						return true;

					q.add(new Node(nx, ny, min, max));
				}
			}
		}
		return false;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++)
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}