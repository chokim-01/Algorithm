import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M, K;
	static int[] numbers;
	static Node[] tree;

	static class Node {
		int value, cnt;

		public Node() {
			// TODO Auto-generated constructor stub
			this.value = 0;
			this.cnt = 0;
		}

		public Node(int v) {
			this.value = v;
			this.cnt = v == K ? 1 : 0;
		}

		public Node(int v, int c) {
			this.value = v;
			this.cnt = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		input(br);
		tree = new Node[N << 2];
		init(1, 1, N);

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
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
		if (b < l || r < a)
			return 0;

		if (a <= l && r <= b)
			return tree[node].cnt;

		int mid = (l + r) / 2;

		return query(node * 2, l, mid, a, b) + query(node * 2 + 1, mid + 1, r, a, b);
	}

	static Node update(int node, int l, int r, int a, int b, int val) {
		if (b < l || r < a)
			return tree[node];
		if (l == r)
			return tree[node] = new Node(tree[node].value | val);

		if ((tree[node].value & val) == val)
			return tree[node];

		int mid = (l + r) / 2;
		Node left = update(node * 2, l, mid, a, b, val);
		Node right = update(node * 2 + 1, mid + 1, r, a, b, val);
		return tree[node] = new Node(left.value & right.value, left.cnt + right.cnt);
	}

	static Node init(int node, int l, int r) {
		if (l == r) {
			return tree[node] = new Node(numbers[l - 1]);
		}

		int mid = (l + r) / 2;
		Node left = init(node * 2, l, mid);
		Node right = init(node * 2 + 1, mid + 1, r);

		return tree[node] = new Node(left.value & right.value, left.cnt + right.cnt);
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = Integer.parseInt(br.readLine());
	}

}