import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int start;
	static ArrayList<Node> con[];

	static class Node implements Comparable<Node> {
		int x, cost;

		public Node(int x, int cost) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		con = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			con[i] = new ArrayList<>();

		start = Integer.parseInt(br.readLine());

		for (int i = 0, a, b, c; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			con[a].add(new Node(b, c));
		}
		int[] ans = dij();

		for (int a : ans) {
			bw.write((a == Integer.MAX_VALUE ? "INF" : a) + "\n");
		}
		bw.flush();
		bw.close();
	}

	static int[] dij() {
		int[] count = new int[N + 1];
		Arrays.fill(count, Integer.MAX_VALUE);
		count[start] = 0;

		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));

		while (!q.isEmpty()) {
			Node n = q.poll();

			int now = n.x;

			if (count[now] < n.cost)
				continue;

			for (Node next : con[now]) {
				int nCost = count[now] + next.cost;
				if (nCost < count[next.x]) {
					count[next.x] = nCost;
					q.add(new Node(next.x, nCost));
				}
			}
		}
		return Arrays.copyOfRange(count, 1, N+1);
	}
}