import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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

		public void update(int node, int l, int r, int s, int e, int val) {
			propagate(node, l, r);
			if (r < s || e < l)
				return;
			if (s <= l && r <= e) {
				pro[node] += val;
				propagate(node, l, r);
				return;
			}
			int mid = (l + r) >> 1;
			update(node << 1, l, mid, s, e, val);
			update(node << 1 | 1, mid + 1, r, s, e, val);
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
		int N = Integer.parseInt(br.readLine());
		int[] boxes = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
		int ans = 1;
		int[] d = new int[N + 1];
		Arrays.fill(d, 1);
		for (int i = 1; i <= N; i++)
			for (int j = 1; j < i; j++)
				if (boxes[j] < boxes[i])
					ans = Math.max(ans, d[i] = Math.max(d[i], d[j] + 1));
		System.out.println(ans);
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