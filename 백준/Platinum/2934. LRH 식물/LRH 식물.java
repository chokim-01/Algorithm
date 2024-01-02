import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int N = 100001;

	static class Seg {
		long[] tree;
		int[] lazy;

		public Seg() {
			// TODO Auto-generated constructor stub
			this.tree = new long[N << 2];
			this.lazy = new int[N << 2];
		}

		long query(int n, int l, int r, int s, int e) {
			propagate(n, l, r);
			if (r < s || l > e)
				return 0;
			if (s <= l && r <= e)
				return tree[n];

			int mid = (l + r) >> 1;
			return tree[n] = query(n << 1, l, mid, s, e) + query(n << 1 | 1, mid + 1, r, s, e);
		}

		void update(int n, int l, int r, int s, int e, int v) {
			propagate(n, l, r);
			if (r < s || l > e)
				return;
			if (s <= l && r <= e) {
				lazy[n] = 1;
				propagate(n, l, r);
				return;
			}
			int mid = (l + r) >> 1;
			update(n << 1, l, mid, s, e, v);
			update(n << 1 | 1, mid + 1, r, s, e, v);
			tree[n] = tree[n << 1] + tree[n << 1 | 1];
		}

		void propagate(int n, int l, int r) {
			if (lazy[n] != 0) {
				tree[n] += (r - l + 1) * lazy[n];
				if (l != r) {
					lazy[n << 1] += lazy[n];
					lazy[n << 1 | 1] += lazy[n];
				}
				lazy[n] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Seg seg = new Seg();

		StringBuilder ans = new StringBuilder();
		long[] flower = new long[N];
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long av = seg.query(1, 1, N, a, a);
			long bv = seg.query(1, 1, N, b, b);
			ans.append(av + bv - flower[a] - flower[b]).append("\n");
			flower[a] = av;
			flower[b] = bv;
			seg.update(1, 1, N, a + 1, b - 1, 1);
		}
		System.out.println(ans);
	}
}
