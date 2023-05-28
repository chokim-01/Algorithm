import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static long[] tree;
	static long[] lazy;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		input(br);
		init(1, 1, N);
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			if (st.hasMoreTokens()) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				update(1, 1, N, a, b, c);
			} else { // query
				ans.append(query(1, 1, N, a, a)).append("\n");
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

	static long query(int node, int l, int r, int a, int b) {
		propagate(node, l, r);
		if (r < a || l > b)
			return 0;
		if (a <= l && r <= b)
			return tree[node];
		int mid = (l + r) / 2;
		return query(node * 2, l, mid, a, b) + query(node * 2 + 1, mid + 1, r, a, b);
	}

	static long init(int node, int l, int r) {
		if (l == r)
			return tree[node] = numbers[l - 1];

		return tree[node] = init(node * 2, l, (l + r) / 2) + init(node * 2 + 1, (l + r) / 2 + 1, r);
	}

	static int cnt = 0;

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new long[N << 2];
		lazy = new long[N << 2];

		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = Integer.parseInt(br.readLine());

	}

}