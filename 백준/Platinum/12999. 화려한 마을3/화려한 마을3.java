import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, mN;
	static int[] cnt, cnt2;
	static int max, index;

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
		M = Integer.parseInt(st.nextToken());
		cnt = new int[100001];
		cnt2 = new int[100001];
		int[] nums = new int[N + 1];

		mN = (int) Math.sqrt(N);
		max = 0;
		index = 0;

		st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (!map.containsKey(n))
				map.put(n, i);
			nums[i] = map.get(n);
		}

		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}

		int[] ans = new int[M];

		Node bef = new Node(1, 1, 0);
		int c = f1(nums[1]);

		while (!q.isEmpty()) {
			Node now = q.poll();
			while (now.x < bef.x)
				// add
				c = f1(nums[--bef.x]);

			while (now.x > bef.x)
				// del
				c = f2(nums[bef.x++]);

			while (now.y < bef.y)
				// del
				c = f2(nums[bef.y--]);

			while (now.y > bef.y)
				// add
				c = f1(nums[++bef.y]);

			ans[now.index] = c;
		}
		StringBuilder sb = new StringBuilder();
		for (int a : ans)
			sb.append(a).append("\n");
		System.out.println(sb);
	}

	static int f1(int n) {
		if (cnt[n]++ >= 0) {
			cnt2[cnt[n] - 1]--;
			cnt2[cnt[n]]++;
		}

		return max = max < cnt[n] ? cnt[n] : max;
	}

	static int f2(int n) {
		if (--cnt[n] >= 0) {
			if (--cnt2[cnt[n] + 1] == 0 && cnt[n] + 1 == max)
				max--;
			cnt2[cnt[n]]++;
		}
		return max;
	}
}