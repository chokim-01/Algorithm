import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] input;
	static final int MOD = 1000000007;

	static class Seg {
		long[] tree;
		long[][] lazy;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new long[(N + 1) << 2];
			lazy = new long[(N + 1) << 2][2];
			for (int i = 1; i < lazy.length; i++)
				lazy[i][1] = 1;

		}

		long init(int node, int l, int r) {
			if (l == r)
				return tree[node] = input[l];
			int mid = (l + r) >> 1;
			return tree[node] = (init(node << 1, l, mid) + init(node << 1 | 1, mid + 1, r)) % MOD;
		}

		long query(int n, int l, int r, int s, int e) {
			propagate(n, l, r);
			if (r < s || l > e)
				return 0;
			if (s <= l && r <= e)
				return tree[n];
			int mid = (l + r) >> 1;
			return (query(n << 1, l, mid, s, e) + query(n << 1 | 1, mid + 1, r, s, e)) % MOD;
		}

		void propagate(int n, int s, int e) {
			int l = n << 1;
			int r = n << 1 | 1;
			if (lazy[n][1] != 1) {
				tree[n] = (tree[n] * lazy[n][1]) % MOD;
				if (s != e) {
					lazy[l][0] = (lazy[l][0] * lazy[n][1]) % MOD;
					lazy[l][1] = (lazy[l][1] * lazy[n][1]) % MOD;
					lazy[r][0] = (lazy[r][0] * lazy[n][1]) % MOD;
					lazy[r][1] = (lazy[r][1] * lazy[n][1]) % MOD;
				}
				lazy[n][1] = 1;
			}
			if (lazy[n][0] != 0) {
				tree[n] = (tree[n] + (e - s + 1) * lazy[n][0]) % MOD;
				if (s != e) {
					lazy[l][0] = (lazy[l][0] + lazy[n][0]) % MOD;
					lazy[r][0] = (lazy[r][0] + lazy[n][0]) % MOD;
				}
				lazy[n][0] = 0;
			}
		}

		// o
		// 1 : plus
		// 2 : mul
		// 3 : change
		void update(int n, int l, int r, int s, int e, long v, int o) {
			propagate(n, l, r);
			if (r < s || l > e)
				return;
			if (s <= l && r <= e) {
				// lazy
				if (o == 1)
					lazy[n][0] = v;
				else if (o == 2)
					lazy[n][1] = v;
				else {
					lazy[n][1] = 0;
					propagate(n, l, r);
					lazy[n][0] = v;
				}
				propagate(n, l, r);
				return;
			}
			int mid = (l + r) >> 1;
			update(n << 1, l, mid, s, e, v, o);
			update(n << 1 | 1, mid + 1, r, s, e, v, o);
			tree[n] = (tree[n << 1] + tree[n << 1 | 1]) % MOD;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

		Seg seg = new Seg();
		seg.init(1, 1, N);
		StringBuilder ans = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			int[] order = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (order[0] < 4)
				seg.update(1, 1, N, order[1], order[2], order[3], order[0]);
			else
				ans.append(seg.query(1, 1, N, order[1], order[2])).append("\n");
		}
		System.out.println(ans);
	}
}
