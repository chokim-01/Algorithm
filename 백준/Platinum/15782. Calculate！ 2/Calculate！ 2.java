import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] link;
	static int[] in, out;
	static int[] numbers, tree, lazy;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		input(br);
		v[1] = true;
		oiler(1);

		for (int i = 1; i <= N; i++)
			update(1, 1, N, in[i], in[i], numbers[i - 1]);

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = 0;
			if (st.hasMoreTokens())
				c = Integer.parseInt(st.nextToken());
			if (a == 1)
				ans.append(query(1, 1, N, in[b], out[b])).append("\n");
			else {
				update(1, 1, N, in[b], out[b], c);
			}
		}
		System.out.println(ans);

	}

	static int query(int node, int l, int r, int a, int b) {
		propagate(node, l, r);
		if (b < l || r < a)
			return 0;

		if (a <= l && r <= b)
			return tree[node];

		int mid = (l + r) / 2;
		return query(node * 2, l, mid, a, b) ^ query(node * 2 + 1, mid + 1, r, a, b);
	}

	static void propagate(int node, int l, int r) {
		if (lazy[node] != 0) {
			tree[node] ^= lazy[node] * ((r - l + 1) % 2);
			if (l != r) {
				lazy[node << 1] ^= lazy[node];
				lazy[(node << 1) + 1] ^= lazy[node];
			}
		}
		lazy[node] = 0;
	}

	static void update(int node, int l, int r, int a, int b, int val) {
		propagate(node, l, r);
		if (b < l || r < a)
			return;
		if (a <= l && r <= b) {
			lazy[node] ^= val;
			propagate(node, l, r);
			return;
		}
		int mid = (l + r) / 2;
		update(node * 2, l, mid, a, b, val);
		update(node * 2 + 1, mid + 1, r, a, b, val);
		tree[node] = tree[node << 1] ^ tree[(node << 1) + 1];
	}

	static int init(int node, int l, int r) {
		if (l == r)
			return tree[node] = numbers[l - 1];

		int mid = (l + r) / 2;
		int left = init(node * 2, l, mid);
		int right = init(node * 2 + 1, mid + 1, r);

		return tree[node] = left ^ right;
	}

	static int cnt = 0;

	static void oiler(int now) {
		in[now] = ++cnt;
		for (int next : link[now]) {
			if (v[next])
				continue;
			v[next] = true;
			oiler(next);
		}
		out[now] = cnt;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		in = new int[N + 1];
		out = new int[N + 1];
		v = new boolean[N + 1];
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}

		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		tree = new int[N << 2];
		lazy = new int[N << 2];
	}

}