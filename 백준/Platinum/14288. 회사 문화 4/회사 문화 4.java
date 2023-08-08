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

		long[] treeDown, treeUp;
		long[] pro;

		public Seg() {
			// TODO Auto-generated constructor stub
			treeUp = new long[N << 2];
			treeDown = new long[N << 2];
			pro = new long[N << 2];
		}

		public void update(int s, int val, boolean flag) {
			if (flag) {
				updateDown(1, 1, N, in[s], out[s], val);
			} else {
				updateUp(s, val);
			}
		}

		public long query(int idx) {
			return queryUp(idx) + queryDown(1, 1, N, in[idx], in[idx]);
		}

		public void updateDown(int node, int l, int r, int s, int e, int val) {
			propagate(node, l, r);
			if (r < s || e < l)
				return;
			if (s <= l && r <= e) {
				pro[node] += val;
				propagate(node, l, r);
				return;
			}
			int mid = (l + r) >> 1;
			updateDown(node << 1, l, mid, s, e, val);
			updateDown(node << 1 | 1, mid + 1, r, s, e, val);
			treeDown[node] = treeDown[node << 1] + treeDown[node << 1 | 1];
		}

		public long queryDown(int node, int l, int r, int s, int e) {
			propagate(node, l, r);
			if (r < s || e < l)
				return 0;
			if (s <= l && r <= e) {
				return treeDown[node];
			}
			int mid = (l + r) >> 1;
			return queryDown(node << 1, l, mid, s, e) + queryDown(node << 1 | 1, mid + 1, r, s, e);
		}

		public void propagate(int idx, int l, int r) {
			if (pro[idx] != 0) {
				treeDown[idx] += pro[idx];
				if (l != r) {
					pro[idx << 1] += pro[idx];
					pro[idx << 1 | 1] += pro[idx];
				}
				pro[idx] = 0;
			}
		}

		public void updateUp(int idx, int v) {
			idx = in[idx] + N - 1;
			do
				treeUp[idx] += v;
			while ((idx >>= 1) != 0);
		}

		public long queryUp(int idx) {
			long ret = 0;
			int l = in[idx] + N - 1;
			int r = out[idx] + N;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret += treeUp[l++];
				if ((r & 1) == 1)
					ret += treeUp[--r];
			}
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		makeTree(br);
		StringBuilder sb = new StringBuilder();
		Seg seg = new Seg();
		boolean dir = true; // true : down | False : up
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if (q == 1) { // update
				int a = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				seg.update(a, v, dir);
			} else if (q == 2) { //
				int a = Integer.parseInt(st.nextToken());
				sb.append(seg.query(a)).append("\n");
			} else
				dir = !dir;
		}
		System.out.println(sb);
	}

	static void makeTree(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		in = new int[N + 1];
		out = new int[N + 1];
		visit = new boolean[N + 1];

		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2, a; i <= N; i++)
			link[Integer.parseInt(st.nextToken())].add(i);

		oiler(1);
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