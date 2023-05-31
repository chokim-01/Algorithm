import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static PriorityQueue<Node> numbers;
	static int[] tree;

	static class Node implements Comparable<Node> {
		int n, i;

		public Node(int n, int i) {
			// TODO Auto-generated constructor stub
			this.n = n;
			this.i = i;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.n - o.n;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		tree = new int[N << 1];

		System.out.println(solve());
	}

	static long solve() {
		long ret = 0;
		while (!numbers.isEmpty()) {
			Node n = numbers.poll();

			ret += query(n.i);
			update(n.i);
		}

		return ret;
	}

	static void update(int id) {
		id += N;
		do
			tree[id] += 1;
		while ((id >>= 1) != 0);

	}

	static int query(int l) {
		int ret = 0;
		l += N;
		int r = tree.length;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				ret += tree[l];
				l++;
			}
			if ((r & 1) == 1) {
				r--;
				ret += tree[r];
			}
		}
		return ret;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		numbers = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			numbers.add(new Node(Integer.parseInt(st.nextToken()), i));

	}

}