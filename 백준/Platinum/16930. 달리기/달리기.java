import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static char[][] map;
	static int[][] visit;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			Arrays.fill(visit[i], Integer.MAX_VALUE);
			for (int j = 0; j < M; j++)
				map[i][j] = s.charAt(j);
		}
		st = new StringTokenizer(br.readLine());
		int sX = Integer.parseInt(st.nextToken()) - 1;
		int sY = Integer.parseInt(st.nextToken()) - 1;
		int eX = Integer.parseInt(st.nextToken()) - 1;
		int eY = Integer.parseInt(st.nextToken()) - 1;

		if (sX == eX && sY == eY)
			System.out.println(0);
		else
			System.out.println(bfs(sX, sY, eX, eY));

	}

	static int bfs(int startX, int startY, int destX, int destY) {
		int ans = -1;
		visit[startX][startY] = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startX, startY));

		int time = 0;
		while (true) {
			int qSize = q.size();
			time += 1;
			for (int i = 0; i < qSize; i++) {
				Node n = q.poll();

				for (int d = 0; d < 4; d++) {
					for (int k = 1; k <= K; k++) {
						int nx = n.x + k * dxy[d][0];
						int ny = n.y + k * dxy[d][1];
						if (!mapChk(nx, ny) || map[nx][ny] == '#')
							break;
						if (nx == destX && ny == destY)
							return time;
						if (visit[nx][ny] == Integer.MAX_VALUE) {
							visit[nx][ny] = time;
							q.add(new Node(nx, ny));
						} else if (visit[nx][ny] < time)
							break;

					}
				}
			}
			if (qSize == 0)
				break;
		}
		return ans;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}