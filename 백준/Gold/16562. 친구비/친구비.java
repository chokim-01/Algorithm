import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int x, cost;

		public Node(int x, int cost) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	static PriorityQueue<Node> human;
	static int[] parent;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		human = new PriorityQueue<>();
		parent = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			human.add(new Node(i, Integer.parseInt(st.nextToken())));
		}

		for (int i = 0, a, b; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		int ans = 0;
		HashSet<Integer> set = new HashSet<>();
		while (!human.isEmpty()) {
			Node n = human.poll();
			int pi = find(n.x);
			if (set.contains(pi))
				continue;
			set.add(pi);
			ans += n.cost;
			if (ans > K) {
				ans = -1;
				break;
			}
		}
		System.out.println(ans == -1 ? "Oh no" : ans);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return;
		if (x < y)
			parent[y] = x;
		else
			parent[x] = y;
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
}