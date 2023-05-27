import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] numbers;
	static int[] tree;
	static int[] lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		input(br);
		init(1, 1, N);

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) + 1;
			int c = Integer.parseInt(st.nextToken()) + 1;
			int d = 0;
			if (st.hasMoreTokens())
				d = Integer.parseInt(st.nextToken());

			if (a == 1)
				update(1, 1, N, b, c, d);
			else
				ans.append(query(1, 1, N, b, c)).append("\n");

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

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = Integer.parseInt(br.readLine());
		tree = new int[N << 2];
		lazy = new int[N << 2];
	}

}