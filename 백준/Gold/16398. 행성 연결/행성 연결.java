import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static HashSet<String> save;
	static ArrayList<Node> link;
	static int[] parent;

	static class Node {
		int start, dest, cost;

		public Node(int s, int d, int c) {
			// TODO Auto-generated constructor stub
			this.start = s;
			this.dest = d;
			this.cost = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		link = new ArrayList<>();
		parent = IntStream.rangeClosed(0, N).map(x -> x).toArray();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < i; j++)
				link.add(new Node(i, j, Integer.parseInt(st.nextToken())));
		}
		link.sort(Comparator.comparingInt((Node n) -> n.cost));
		long ans = 0;
		for (Node n : link) {
			if (find(n.start) == find(n.dest))
				continue;
			union(n.start, n.dest);
			ans += n.cost;
		}
		System.out.println(ans);
	}

	static int find(int x) {
		if (x == parent[x])
			return x;

		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x < y)
			parent[x] = y;
		else
			parent[y] = x;

	}
}