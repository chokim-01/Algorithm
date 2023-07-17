import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, sqrtN;
	static long[] cnt;
	static int max, index;

	static class Node implements Comparable<Node> {
		int x, y, index, mN;

		public Node(int x, int y, int index) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.index = index;
			this.mN = this.x / sqrtN;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.mN == o.mN)
				return this.y - o.y;
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sqrtN = (int) Math.sqrt(N);
		cnt = new long[1000001];
		int nums[] = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}
		long ans[] = new long[M];

		Node bef = q.poll();
		long c = 0;
		for (int i = bef.x; i <= bef.y; i++)
			c = add(nums[i], c);
		ans[bef.index] = c;

		while (!q.isEmpty()) {
			Node now = q.poll();
			while (now.x < bef.x)
				// add
				c = add(nums[--bef.x], c);

			while (now.x > bef.x)
				// del
				c = del(nums[bef.x++], c);

			while (now.y < bef.y)
				// del
				c = del(nums[bef.y--], c);

			while (now.y > bef.y)
				// add
				c = add(nums[++bef.y], c);

			ans[now.index] = c;
		}
		StringBuilder sb = new StringBuilder();
		for (long a : ans)
			sb.append(a).append("\n");
		System.out.println(sb);

	}

	static long add(int n, long c) {
		cnt[n]++;
		c += cnt[n] * cnt[n] * n - (cnt[n] - 1) * (cnt[n] - 1) * n;
		return c;
	}

	static long del(int n, long c) {
		cnt[n]--;
		c += (cnt[n]) * (cnt[n]) * n - (cnt[n] + 1) * (cnt[n] + 1) * n;
		return c;
	}
}