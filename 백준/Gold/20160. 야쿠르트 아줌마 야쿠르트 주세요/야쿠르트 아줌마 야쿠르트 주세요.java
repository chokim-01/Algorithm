import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] route;
	static ArrayList<Node>[] link;

	static class Node implements Comparable<Node> {
		int n, v;

		public Node(int d, int v) {
			// TODO Auto-generated constructor stub
			this.n = d;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.v - o.v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점
		M = Integer.parseInt(st.nextToken()); // 도로

		link = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			link[i] = new ArrayList<>();

		for (int i = 0, a, b, c; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			link[a].add(new Node(b, c));
			link[b].add(new Node(a, c));
		}

		int[] ya = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int now = Integer.parseInt(br.readLine());
		int[] nCost = dij(now);

		int ans = Integer.MAX_VALUE;
		int yNow = ya[0];
		long yC = 0;
		// 가장 작은 번호 출력
		if (ya[0] == now)
			ans = ya[0];
		for (int i = 1; i < 10; i++) {
			int[] yCost = dij(yNow);
			if (yCost[ya[i]] == Integer.MAX_VALUE)
				continue;

			yC += yCost[ya[i]];
			if (nCost[ya[i]] <= yC)
				if (ans > ya[i])
					ans = ya[i];

			yNow = ya[i];
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static int[] dij(int now) {
		int[] c = new int[N + 1];
		Arrays.fill(c, Integer.MAX_VALUE);

		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(now, 0));
		c[now] = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			int cost = n.v;
			now = n.n;

			if (c[now] < cost)
				continue;
			for (Node next : link[now]) {
				int nCost = c[now] + next.v;
				if (nCost >= c[next.n])
					continue;
				c[next.n] = nCost;
				q.add(new Node(next.n, nCost));
			}
		}
		return c;
	}
	/*
	 * 10개 지점 최단 시간 같거나 일찍 도착한 사람에게 건넴 dij
	 */
}