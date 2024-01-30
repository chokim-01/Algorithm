import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static class FWick {
		long[][] tree;

		public FWick() {
			// TODO Auto-generated constructor stub
			tree = new long[5003][5003];
		}

		long query(int a, int b) {
			long ret = 0;
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		FWick fwick = new FWick();
		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("1")) {
				// u
				int a = Integer.parseInt(st.nextToken()) + 1;
				int b = Integer.parseInt(st.nextToken()) + 1;
				int c = Integer.parseInt(st.nextToken()) + 1;
				int d = Integer.parseInt(st.nextToken()) + 1;
				int e = Integer.parseInt(st.nextToken());
				fwick.update(c, d, e);
				fwick.update(a - 1, b - 1, e);
				fwick.update(a - 1, d, -e);
				fwick.update(c, b - 1, -e);
			} else {
				// q
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long ans = fwick.query(a, b);
				sb.append(ans).append("\n");
			}
		}
		System.out.println(sb);
	}
}