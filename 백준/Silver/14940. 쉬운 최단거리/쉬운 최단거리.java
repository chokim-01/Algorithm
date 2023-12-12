import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] se = { 0, 0 };
		boolean[][] map = new boolean[N][M];
		int[][] ans = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 2)
					se = new int[] { i, j };
				map[i][j] = n == 1 ? true : false;
			}
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.add(se);
		int c = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			c++;
			while (size-- > 0) {
				int[] n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n[0] + dxy[d][0];
					int ny = n[1] + dxy[d][1];
					if (!mapChk(nx, ny, N, M) || !map[nx][ny])
						continue;
					map[nx][ny] = false;
					ans[nx][ny] = c;
					q.add(new int[] { nx, ny });
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(map[i][j] && ans[i][j] == 0 ? -1 : ans[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean mapChk(int x, int y, int N, int M) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}
