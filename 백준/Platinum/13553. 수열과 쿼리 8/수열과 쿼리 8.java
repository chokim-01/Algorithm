import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, mN, tN = 100001;
	static Seg seg;

	static class Seg {
		long[] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			this.tree = new long[tN << 2];
		}

		void update(int i, int v) {
			i += tN;
			do
				tree[i] += v;
			while ((i >>= 1) != 0);
		}

		int query(int l, int r) {
			l += tN;
			r += tN + 1;
			int ret = 0;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret += tree[l++];
				if ((r & 1) == 1)
					ret += tree[--r];
			}
			return ret;
		}
	}

	static class Node implements Comparable<Node> {
		int x, y, index;

		public Node(int x, int y, int index) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.x / mN == o.x / mN)
				return this.y - o.y;
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		seg = new Seg();
		int[] nums = new int[N + 1];

		mN = (int) Math.sqrt(N);

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}
		long[] ans = new long[M];

		Node bef = new Node(1, 1, 0);
		long c = f1(nums[1]);
		while (!q.isEmpty()) {
			Node now = q.poll();
			while (now.x < bef.x)
				// add
				c += f1(nums[--bef.x]);

			while (now.x > bef.x)
				// del
				c -= f2(nums[bef.x++]);

			while (now.y < bef.y)
				// del
				c -= f2(nums[bef.y--]);

			while (now.y > bef.y)
				// add
				c += f1(nums[++bef.y]);
			ans[now.index] = c;
		}
		StringBuilder sb = new StringBuilder();
		for (long a : ans)
			sb.append(a).append("\n");
		System.out.println(sb);
	}

	static int f1(int n) {
		int ret = seg.query(Math.max(1, n - K), Math.min(tN, n + K));
		seg.update(n, 1);
		return ret;
	}

	// x-k <= y <= x+k
	static int f2(int n) {
		seg.update(n, -1);
		int ret = seg.query(Math.max(1, n - K), Math.min(tN, n + K));
		return ret;
	}
}