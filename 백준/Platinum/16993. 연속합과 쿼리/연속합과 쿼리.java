import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;

	static class Node {
		long lSum, rSum, aSum, mSum;

		public Node() {
			// TODO Auto-generated constructor stub
			this.lSum = -100000001;
			this.rSum = -100000001;
			this.aSum = 0;
			this.mSum = -100000001;
		}

		public Node(int n) {
			this.lSum = n;
			this.rSum = n;
			this.aSum = n;
			this.mSum = n;
		}

		public Node(long lSum, long rSum, long aSum, long mSum) {
			this.lSum = lSum;
			this.rSum = rSum;
			this.aSum = aSum;
			this.mSum = mSum;
		}

		@Override
		public String toString() {
			return this.lSum + " " + this.rSum + " " + this.aSum + " " + this.mSum;
		}

	}

	static class Seg {
		Node[] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new Node[N << 2];
			for (int i = 0; i < tree.length; i++) {
				tree[i] = new Node();
			}
		}

		Node makeNode(Node left, Node right) {
			Node node = new Node();
			node.lSum = Math.max(left.lSum, left.aSum + right.lSum);
			node.rSum = Math.max(right.rSum, right.aSum + left.rSum);
			node.aSum = left.aSum + right.aSum;
			node.mSum = Math.max(left.rSum + right.lSum, Math.max(left.mSum, right.mSum));
			return node;
		}

		void init(int node, int l, int r, int[] numbers) {
			if (l == r) {
				tree[node] = new Node(numbers[l - 1]);
				return;
			}
			int mid = (l + r) >> 1;
			init(node << 1, l, mid, numbers);
			init(node << 1 | 1, mid + 1, r, numbers);
			tree[node] = makeNode(tree[node << 1], tree[node << 1 | 1]);
		}

		Node query(int node, int l, int r, int s, int e) {
			if (r < s || e < l)
				return new Node();
			if (s <= l && r <= e) {
				return tree[node];
			}
			int mid = (l + r) >> 1;
			return makeNode(query(node << 1, l, mid, s, e), query(node << 1 | 1, mid + 1, r, s, e));
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Seg seg = new Seg();
		seg.init(1, 1, N, nums);
		M = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			answer.append(seg.query(1, 1, N, a, b).mSum).append("\n");
		}
		System.out.println(answer);
	}
}