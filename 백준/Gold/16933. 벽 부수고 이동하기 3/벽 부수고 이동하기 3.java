import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static class Node {
		int x;
		int y;
		int k;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.k = 0;
			// TODO Auto-generated constructor stub
		}

		public Node(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
			// TODO Auto-generated constructor stub
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		boolean[][] map = new boolean[N][M];
		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++)
				map[i][j] = s.charAt(j) == '1' ? false : true;
		}

		Queue<Node> q = new LinkedList<>();

		int[][][] visit = new int[N][M][2];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				Arrays.fill(visit[i][j], 100);
		q.add(new Node(0, 0));
		int time = 1;

		outer: while (!q.isEmpty()) {
			int qsize = q.size();
			boolean naFlag = time % 2 == 1 ? true : false; // 낮 , 밤
			for (int i = 0; i < qsize; i++) {
				Node n = q.poll();
				if (n.x == N - 1 && n.y == M - 1) {
					ans = time;
					break outer;
				}
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];

					// 방문 또는 맵 넘어갔을 때
					if (!mapChk(nx, ny) || n.k >= visit[nx][ny][naFlag ? 0 : 1])
						continue;

					if (naFlag) { // 벽은 낮에만 부술 수 있다.
						if (!map[nx][ny] && visit[nx][ny][naFlag ? 0 : 1] > n.k + 1) {
							if (n.k + 1 <= K) {
								q.add(new Node(nx, ny, n.k + 1));
								visit[nx][ny][naFlag ? 0 : 1] = n.k + 1;
							}
						}
						// 밤에만 머무르는게 의미가 있음
					} else {
						q.add(new Node(n.x, n.y, n.k));
					}

					// 낮 + 밤
					if (map[nx][ny] && visit[nx][ny][naFlag ? 0 : 1] > n.k) {
						q.add(new Node(nx, ny, n.k));
						visit[nx][ny][naFlag ? 0 : 1] = n.k;
					}

				}
			}
			time += 1;
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;

	}
}