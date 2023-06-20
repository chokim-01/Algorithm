import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static Node[] tree;
	static int[] numbers;

	static class Node {
		long max, max2, maxCount;
		long sum;

		public Node(long max, long max2, long maxCount, long sum) {
			// TODO Auto-generated constructor stub
			this.max = max;
			this.max2 = max2;
			this.maxCount = maxCount;
			this.sum = sum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		input(br);
		init(1, 1, N);
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (q == 1) {
				int c = Integer.parseInt(st.nextToken());
				update(1, 1, N, a, b, c);
			} else if (q == 2) {
				ans.append(queryMax(1, 1, N, a, b)).append("\n");
			} else {
				ans.append(querySum(1, 1, N, a, b)).append("\n");
			}
		}
		System.out.println(ans);
	}

	static void propagate(int node, int l, int r) {
		if (l == r)
			return;
		for (int i = node << 1; i < (node << 1) + 2; i++) {
			if (tree[node].max < tree[i].max) {
				tree[i].sum -= tree[i].maxCount * (tree[i].max - tree[node].max);
				tree[i].max = tree[node].max;
			}
		}
	}

	static boolean breakCondition(int node, int l, int r, int a, int b, long v) {
		if (r < a || l > b || tree[node].max <= v)
			return false;
		return true;
	}

	static boolean changeCondition(int node, int l, int r, int a, int b, int v) {
		if (a <= l && r <= b && tree[node].max2 < v)
			return true;
		return false;
	}

	static void update(int node, int l, int r, int a, int b, int val) {
		propagate(node, l, r);
		if (!breakCondition(node, l, r, a, b, val))
			return;
		if (changeCondition(node, l, r, a, b, val)) {
			tree[node].sum -= tree[node].maxCount * (tree[node].max - val);
			tree[node].max = val;
			propagate(node, l, r);
			return;
		}
		int mid = (l + r) / 2;
		update(node * 2, l, mid, a, b, val);
		update(node * 2 + 1, mid + 1, r, a, b, val);
		tree[node] = combineNode(tree[node << 1], tree[(node << 1) + 1]);
	}

	static long queryMax(int node, int l, int r, int a, int b) {
		propagate(node, l, r);
		if (r < a || l > b)
			return 0;
		if (a <= l && r <= b)
			return tree[node].max;
		int mid = (l + r) / 2;
		return Math.max(queryMax(node * 2, l, mid, a, b), queryMax(node * 2 + 1, mid + 1, r, a, b));
	}

	static long querySum(int node, int l, int r, int a, int b) {
		propagate(node, l, r);
		if (r < a || l > b)
			return 0;
		if (a <= l && r <= b)
			return tree[node].sum;
		int mid = (l + r) / 2;
		return querySum(node * 2, l, mid, a, b) + querySum(node * 2 + 1, mid + 1, r, a, b);
	}

	static Node init(int node, int l, int r) {
		if (l == r)
			return tree[node] = new Node(numbers[l - 1], -1, 1, numbers[l - 1]);
		Node left = init(node * 2, l, (l + r) / 2);
		Node right = init(node * 2 + 1, (l + r) / 2 + 1, r);
		return tree[node] = combineNode(left, right);
	}

	static Node combineNode(Node left, Node right) {
		if (left.max == right.max)
			return new Node(left.max, Math.max(left.max2, right.max2), left.maxCount + right.maxCount,
					left.sum + right.sum);
		else if (left.max > right.max)
			return new Node(left.max, Math.max(left.max2, right.max), left.maxCount, left.sum + right.sum);
		return new Node(right.max, Math.max(left.max, right.max2), right.maxCount, left.sum + right.sum);
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		tree = new Node[N << 2];

		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = Integer.parseInt(br.readLine());
	}
}