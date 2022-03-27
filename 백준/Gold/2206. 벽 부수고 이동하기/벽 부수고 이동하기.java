import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static class Node {
		int x;
		int y;
		boolean flag;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.flag = false;
			// TODO Auto-generated constructor stub
		}

		public Node(int x, int y, boolean flag) {
			this.x = x;
			this.y = y;
			this.flag = flag;
			// TODO Auto-generated constructor stub
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		boolean[][] map = new boolean[N][M];
		int ans = -1;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++)
				map[i][j] = s.charAt(j) == '1' ? false : true;
		}

		Queue<Node> q = new LinkedList<>();

		boolean[][][] visit = new boolean[N][M][2];
		q.add(new Node(0, 0));
		visit[0][0][0] = true;
		int time = 1;

		outer: while (!q.isEmpty()) {
			int qsize = q.size();

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
					if (!mapChk(nx, ny) || visit[nx][ny][n.flag?1:0])
						continue;
					visit[nx][ny][n.flag?1:0] = true;
					
					// 벽이면서 flag == false 일때
					if(!map[nx][ny] && !n.flag)
						q.add(new Node(nx, ny,true));
					
					// 벽일 때
					if(!map[nx][ny])
						continue;

					q.add(new Node(nx,ny,n.flag));

				}

			}
			time += 1;
		}
		System.out.println(ans);
		

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;

	}
}