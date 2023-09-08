import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static int[] in, out;
	static boolean[] visit;
	static ArrayList<Integer>[] link;

	static class Seg {
		long[] tree;
		long[] pro;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new long[N << 2];
			pro = new long[N << 2];
		}

		public void update(int node, int l, int r, int n, int v) {
			if (r < n || n < l)
				return;
			if (l == n && n == r) {
				tree[node] = v;
				return;
			}
			int mid = (l + r) >> 1;
			update(node << 1, l, mid, n, v);
			update(node << 1 | 1, mid + 1, r, n, v);
			tree[node] = tree[node << 1] + tree[node << 1 | 1];
		}

		public void updateRange(int node, int l, int r, int s, int e, int val) {
			propagate(node, l, r);
			if (r < s || e < l)
				return;
			if (s <= l && r <= e) {
				pro[node] += val;
				propagate(node, l, r);
				return;
			}
			int mid = (l + r) >> 1;
			updateRange(node << 1, l, mid, s, e, val);
			updateRange(node << 1 | 1, mid + 1, r, s, e, val);
			tree[node] = tree[node << 1] + tree[node << 1 | 1];
		}

		public long query(int node, int l, int r, int s, int e) {
			propagate(node, l, r);
			if (r < s || e < l)
				return 0;
			if (s <= l && r <= e) {
				return tree[node];
			}
			int mid = (l + r) >> 1;
			return query(node << 1, l, mid, s, e) + query(node << 1 | 1, mid + 1, r, s, e);
		}

		public void propagate(int idx, int l, int r) {
			if (pro[idx] != 0) {
				tree[idx] += pro[idx];
				if (l != r) {
					pro[idx << 1] += pro[idx];
					pro[idx << 1 | 1] += pro[idx];
				}
				pro[idx] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] salary = makeTree(br);
		StringBuilder sb = new StringBuilder();
		Seg seg = new Seg();
		for (int i = 1; i <= N; i++)
			seg.update(1, 1, N, in[i], salary[i]);
		
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			String o = st.nextToken();
			if (o.equals("p")) { // add
				int a = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				seg.updateRange(1, 1, N, in[a] + 1, out[a], v);
			} else { // print
				int a = Integer.parseInt(st.nextToken());
				sb.append(seg.query(1, 1, N, in[a], in[a])).append("\n");
			}
		}
		System.out.println(sb);
	}

	static int[] makeTree(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		in = new int[N + 1];
		out = new int[N + 1];
		visit = new boolean[N + 1];

		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();

		int[] salary = new int[N + 1];
		salary[1] = Integer.parseInt(br.readLine());

		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			salary[i] = Integer.parseInt(st.nextToken());
			link[Integer.parseInt(st.nextToken())].add(i);
		}

		oiler(1);
		return salary;
	}

	static int cnt = 0;

	static void oiler(int now) {
		in[now] = ++cnt;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			oiler(next);
		}
		out[now] = cnt;
	}
}