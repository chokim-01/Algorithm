import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static HLD hld;
	static Seg seg;

	static class Seg {
		int[] tree;
		int[] lazy;

		public Seg(int n) {
			// TODO Auto-generated constructor stub
			tree = new int[n << 2];
			lazy = new int[n << 2];
		}

		void propagate(int node, int l, int r) {
			if (lazy[node] != 0) {
				tree[node] += (r - l + 1) * lazy[node];
				if (l != r) {
					lazy[node << 1] += lazy[node];
					lazy[node << 1 | 1] += lazy[node];
				}
			}
			lazy[node] = 0;
		}

		void update(int node, int l, int r, int s, int e) {
			// lazy
			propagate(node, l, r);
			if (l > e || r < s)
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

		int query(int node, int l, int r, int s, int e) {
			// lazy
			propagate(node, l, r);
			if (l > e || r < s)
				return 0;

			if (s <= l && r <= e)
				return tree[node];

			int mid = (l + r) >> 1;
			return query(node << 1, l, mid, s, e) + query(node << 1 | 1, mid + 1, r, s, e);
		}
	}

	static class HLD {
		int[] top, parent, size;
		int[] in, out, depth;
		boolean[] visit;
		ArrayList<Integer>[] link;
		int count = 0;

		public HLD() {
			// TODO Auto-generated constructor stub
			top = new int[N];
			parent = new int[N];
			size = new int[N];
			in = new int[N];
			out = new int[N];
			depth = new int[N];
			link = new ArrayList[N];
			for (int i = 0; i < N; i++)
				link[i] = new ArrayList<>();
		}

		void dfs1(int now, int prev) {
			size[now] = 1;
			parent[now] = prev;
			for (int i = 0; i < link[now].size(); i++) {
				int next = link[now].get(i);
				if (next == prev)
					continue;
				depth[next] = depth[now] + 1;

				dfs1(next, now);

				size[now] += size[next];
				if (link[now].get(0) == prev || size[link[now].get(0)] < size[next]) {
					Collections.swap(link[now], i, 0);
				}
			}
		}

		void dfs2(int now, int prev) {
			in[now] = ++count;
			for (int next : link[now]) {
				if (next == prev)
					continue;
				top[next] = next == link[now].get(0) ? top[now] : next;
				dfs2(next, now);
			}
			out[now] = count;
		}

		int getAns(int a, int b) {

			int x = a;
			int y = b;
			int answer = 0;
			while (top[x] != top[y]) {
				if (depth[top[x]] < depth[top[y]]) {
					int tmp = x;
					x = y;
					y = tmp;
				}
				answer += seg.query(1, 1, N, in[top[x]], in[x]);
				x = parent[top[x]];
			}
			if (in[x] > in[y]) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			return answer + seg.query(1, 1, N, in[x] + 1, in[y]);
		}

		void update(int a, int b) {

			int x = a;
			int y = b;
			while (top[x] != top[y]) {
				if (depth[top[x]] < depth[top[y]]) {
					int tmp = x;
					x = y;
					y = tmp;
				}
				seg.update(1, 1, N, in[top[x]], in[x]);
				x = parent[top[x]];
			}
			if (in[x] > in[y]) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			seg.update(1, 1, N, in[x] + 1, in[y]);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		groundWork(br);

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String o = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (o.equals("P")) { // range update
				hld.update(a, b);
			} else { // query
				ans.append(hld.getAns(a, b)).append("\n");
			}
		}
		System.out.println(ans);
	}

	static void groundWork(BufferedReader br) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());

		hld = new HLD();
		seg = new Seg(N);

		for (int i = 1; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			hld.link[a].add(b);
			hld.link[b].add(a);
		}
		hld.dfs1(1, 0);
		hld.dfs2(1, 0);
	}

}
