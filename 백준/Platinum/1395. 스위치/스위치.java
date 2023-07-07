import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static class Seg {
		int[] tree;
		int[] lazy;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new int[N << 2];
			lazy = new int[N << 2];
		}

		public void propagate(int node, int l, int r) {
			if (lazy[node] % 2 == 1) {
				tree[node] = (r - l + 1) - tree[node];
				if (l != r) {
					lazy[node << 1] += 1;
					lazy[node << 1 | 1] += 1;
				}
				lazy[node] = 0;
			}
		}

		public void update(int node, int l, int r, int s, int e) {
			// lazy
			propagate(node, l, r);
			if (r < s || l > e)
				return;
			if (s <= l && r <= e) {
				lazy[node] += 1;
				propagate(node, l, r);
				return;
			}
			int mid = (l + r) >> 1;
			update(node << 1, l, mid, s, e);
			update(node << 1 | 1, mid + 1, r, s, e);
			tree[node] = tree[node << 1] + tree[node << 1 | 1];
		}

		public int query(int node, int l, int r, int s, int e) {
			// lazy
			propagate(node, l, r);
			if (r < s || l > e)
				return 0;
			if (s <= l && r <= e)
				return tree[node];
			int mid = (l + r) >> 1;
			return query(node << 1, l, mid, s, e) + query(node << 1 | 1, mid + 1, r, s, e);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());

		Seg seg = new Seg();
		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (o == 0) { // update
				seg.update(1, 1, N, a, b);
			} else { // query
				answer.append(seg.query(1, 1, N, a, b)).append("\n");
			}
		}
		System.out.println(answer);

	}
}
