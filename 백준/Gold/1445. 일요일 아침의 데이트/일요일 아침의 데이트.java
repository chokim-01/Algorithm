import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visit;

	static class Node implements Comparable<Node> {
		int x, y;
		int cnt1, cnt2;

		public Node(int x, int y, int cnt1, int cnt2) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.cnt1 = cnt1;
			this.cnt2 = cnt2;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.cnt1 == o.cnt1)
				return this.cnt2 - o.cnt2;
			return this.cnt1 - o.cnt1;
		}

		@Override
		public String toString() {
			return "[ " + this.x + " , " + this.y + " , " + this.cnt1 + " , " + this.cnt2 + " ] ";
		}
	}

	static int dxy[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M];
		boolean[][] choice = new boolean[N][M];
		int[] start = new int[2];
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == 'g') {
					for (int d = 0, nx, ny; d < 4; ++d) {
						nx = i + dxy[d][0];
						ny = j + dxy[d][1];

						if (!mapChk(nx, ny))
							continue;
						choice[nx][ny] = true;
					}
				} else if (map[i][j] == 'S')
					start = new int[] { i, j };
			}
		}

		PriorityQueue<Node> q = new PriorityQueue<>();
		visit[start[0]][start[1]] = true;
		q.add(new Node(start[0], start[1], 0, 0));
		int cnt1 = 0;
		int cnt2 = 0;
		while (!q.isEmpty()) {
			Node now = q.poll();
			if (map[now.x][now.y] == 'F') {
				cnt1 = now.cnt1;
				cnt2 = now.cnt2;
				break;
			}

			for (int d = 0, nx, ny; d < 4; ++d) {
				nx = now.x + dxy[d][0];
				ny = now.y + dxy[d][1];

				if (!mapChk(nx, ny) || visit[nx][ny])
					continue;

				visit[nx][ny] = true;
				cnt1 = now.cnt1;
				cnt2 = now.cnt2;
				if (map[nx][ny] == 'g')
					cnt1++;
				else if (map[nx][ny] == '.' && choice[nx][ny])
					cnt2++;
				q.add(new Node(nx, ny, cnt1, cnt2));
			}
		}
		System.out.println(cnt1+ " " + cnt2);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}