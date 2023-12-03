import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = st.nextToken().equals("1") ? true : false;
		}
		boolean[][] v = new boolean[N][M];
		int a1 = 0;
		int a2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (v[i][j] || !map[i][j])
					continue;
				a1++;
				Queue<int[]> q = new ArrayDeque<>();
				v[i][j] = true;
				q.add(new int[] { i, j });
				int mx = 1;
				while (!q.isEmpty()) {
					int[] n = q.poll();
					for (int d = 0; d < 4; d++) {
						int nx = n[0] + dxy[d][0];
						int ny = n[1] + dxy[d][1];

						if (nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny] || !map[nx][ny])
							continue;
						v[nx][ny] = true;
						mx++;
						q.add(new int[] { nx, ny });
					}
				}
				a2 = a2 < mx ? mx : a2;
			}
		}
		System.out.println(ans.append(a1).append("\n").append(a2));

	}
}