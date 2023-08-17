import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, P;
	static int[] cost;
	static boolean[] v;
	static ArrayList<Node> link[];

	static class Node implements Comparable<Node> {
		int d, w;

		public Node(int d, int w) {
			// TODO Auto-generated constructor stub
			this.d = d;
			this.w = w;

		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		int ans = Integer.MAX_VALUE;
		link = new ArrayList[N + 1];
		cost = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			link[i] = new ArrayList<>();
			ans = Math.min(ans, cost[i] = Integer.parseInt(br.readLine()));
		}

		while (P-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			link[a].add(new Node(b, cost[a] + cost[b] + 2 * c));
			link[b].add(new Node(a, cost[a] + cost[b] + 2 * c));
		}
		PriorityQueue<Node> q = new PriorityQueue<>();
		v = new boolean[N + 1];
		q.add(new Node(1, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (v[n.d])
				continue;
			v[n.d] = true;
			ans += n.w;

			for (Node next : link[n.d]) {
				if (v[next.d])
					continue;
				q.add(new Node(next.d, next.w));
			}
		}
		System.out.println(ans);

	}
}