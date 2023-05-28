import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] tree;
	static int[] lazy;
	static int[] numbers;
	static int[] in;
	static int[] out;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		input(br);
		makeLink();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			if (st.hasMoreTokens()) {
				int b = Integer.parseInt(st.nextToken());
				update(1, 1, N, in[a], out[a], b);
			} else { // query
				ans.append(query(1, 1, N, in[a], in[a])).append("\n");
			}
		}
		System.out.println(ans);
	}

	static void propagate(int node, int l, int r) {
		if (lazy[node] == 0)
			return;
		tree[node] += lazy[node] * (r - l + 1);
		if (l != r) {
			lazy[node << 1] += lazy[node];
			lazy[(node << 1) + 1] += lazy[node];
		}
		lazy[node] = 0;
	}

	static void update(int node, int l, int r, int a, int b, int val) {
		propagate(node, l, r);
		if (r < a || l > b)
			return;
		if (a <= l && r <= b) {
			lazy[node] += val;
			propagate(node, l, r);
			return;
		}
		int mid = (l + r) / 2;
		update(node * 2, l, mid, a, b, val);
		update(node * 2 + 1, mid + 1, r, a, b, val);
		tree[node] = tree[node << 1] + tree[(node << 1) + 1];
	}

	static int query(int node, int l, int r, int a, int b) {
		propagate(node, l, r);
		if (r < a || l > b)
			return 0;
		if (a <= l && r <= b)
			return tree[node];
		int mid = (l + r) / 2;
		return query(node * 2, l, mid, a, b) + query(node * 2 + 1, mid + 1, r, a, b);
	}

	static void makeLink() {

		ArrayList<Integer>[] link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		for (int i = 1; i < numbers.length; i++)
			link[numbers[i]].add(i + 1);
		
		dfs(1, link);
	}

	static int cnt = 0;

	static void dfs(int now, ArrayList<Integer>[] link) {
		in[now] = ++cnt;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			dfs(next, link);
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
		tree = new int[N << 2];
		lazy = new int[N << 2];
		visit = new boolean[N + 1];
		visit[1] = true;

		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

	}

}