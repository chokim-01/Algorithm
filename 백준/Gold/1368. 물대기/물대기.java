import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static int[] parent;
	static PriorityQueue<Node> list;

	static class Node implements Comparable<Node> {
		int x, y, c;

		public Node(int x, int y, int c) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub

			return this.c - o.c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		parent = IntStream.rangeClosed(0, N).map(x -> x).toArray();
		list = new PriorityQueue<>();
		for (int i = 1; i <= N; i++)
			list.add(new Node(0, i, Integer.parseInt(br.readLine())));

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < i; j++)
				list.add(new Node(i, j, Integer.parseInt(st.nextToken())));
		}
		long ans = 0;
		while (!list.isEmpty()) {
			Node n = list.poll();
			int a = n.x;
			int b = n.y;
			if (find(a) == find(b))
				continue;
			union(a, b);
			ans += n.c;
		}
		System.out.println(ans);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x < y)
			parent[x] = y;
		else
			parent[y] = x;
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
}