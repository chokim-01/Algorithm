import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Main {
	static class Node implements Comparable<Node> {
		int x, y, cnt;

		public Node(int x, int y, int cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] map = new int[5][5];
		Arrays.setAll(map, i -> {
			try {
				return Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		});
		int[] xy = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		boolean[][] v = new boolean[5][5];
		PriorityQueue<Node> q = new PriorityQueue<>();
		v[xy[0]][xy[1]] = true;
		q.add(new Node(xy[0], xy[1], 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (map[n.x][n.y] == 1) {
				System.out.println(n.cnt);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk(nx, ny) || map[nx][ny] == -1 || v[nx][ny])
					continue;
				v[nx][ny] = true;
				q.add(new Node(nx, ny, n.cnt + 1));
			}
			for (int d = 0; d < 4; d++) {
				int nx = n.x;
				int ny = n.y;
				while (mapChk(nx += dxy[d][0], ny += dxy[d][1]) && map[nx][ny] != -1 && map[nx][ny] != 7)
					;
				if (!mapChk(nx, ny) || map[nx][ny] == -1) {
					nx -= dxy[d][0];
					ny -= dxy[d][1];
					if (v[nx][ny])
						continue;
					v[nx][ny] = true;
					q.add(new Node(nx, ny, n.cnt + 1));

				} else if (map[nx][ny] == 7) {
					if (v[nx][ny])
						continue;
					v[nx][ny] = true;
					q.add(new Node(nx, ny, n.cnt + 1));
				}
			}

		}
		System.out.println(-1);

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= 5 || y >= 5)
			return false;
		return true;
	}
}
