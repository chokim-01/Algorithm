import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class FWick {
		long[][] tree;

		public FWick(int N) {
			// TODO Auto-generated constructor stub
			tree = new long[N + 1][N + 1];
		}

		int query(int a, int b) {
			int ret = 0;
			int x = a;
			for (; x > 0; x -= x & -x) {
				int y = b;
				for (; y > 0; y -= y & -y)
					ret += tree[x][y];
			}

			return ret;
		}

		void update(int a, int b, int v) {
			int x = a;
			for (; x < tree.length; x += x & -x) {
				int y = b;
				for (; y < tree.length; y += y & -y)
					tree[x][y] += v;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		FWick fwick = new FWick(N);
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				fwick.update(i, j, map[i][j]);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("0")) {
				// u
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = c - map[a][b];
				map[a][b] = c;
				fwick.update(a, b, d);

			} else {
				// q
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int ans = fwick.query(c, d) + fwick.query(a - 1, b - 1) - fwick.query(c, b - 1) - fwick.query(a - 1, d);
				sb.append(ans).append("\n");
			}
		}
		System.out.println(sb);
	}
}