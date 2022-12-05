import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[] parent;

	static class Node implements Comparable<Node> {
		int num;
		int x;

		public Node(int num, String x) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.x = Integer.parseInt(x);
		}

		@Override
		public int compareTo(Node n) {

			return this.x - n.x;
		}
	}

	static class Edge implements Comparable<Edge> {
		int x, y;
		int v;

		public Edge(int x, int y, int v) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compareTo(Edge e) {
			return this.v - e.v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[N];

		ArrayList<Node> list[] = new ArrayList[3];
		for (int i = 0; i < 3; i++)
			list[i] = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int a = 0; a < 3; a++)
				list[a].add(new Node(i, st.nextToken()));
		}
		for (int i = 0; i < 3; i++)
			Collections.sort(list[i]);

		ArrayList<Edge> edges = new ArrayList<>();

		for (int i = 1; i < N; i++) {
			parent[i] = i;
			for (int a = 0; a < 3; a++) {
				Node n = list[a].get(i - 1);
				Node n2 = list[a].get(i);
				int v = Math.abs(n.x - n2.x);
				edges.add(new Edge(n.num, n2.num, v));
			}
		}
		Collections.sort(edges);

		int ans = 0;
		for (Edge e : edges)
			if (union(e.x, e.y))
				ans += e.v;

		System.out.println(ans);
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		if (x < y)
			parent[y] = x;
		else
			parent[x] = y;
		return true;
	}
}