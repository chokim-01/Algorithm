import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int P, W;

	static class Node implements Comparable<Node> {
		int x, y, cost;

		public Node(int x, int y, int cost) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return o.cost - this.cost;
		}
	}

	static ArrayList<Node> list;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		parent = new int[P];
		for (int i = 0; i < P; i++)
			parent[i] = i;
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		for (int w = 0, a, b, cost; w < W; w++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			list.add(new Node(a, b, cost));
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			Node n = list.get(i);
			union(n.x, n.y);
			
			if(find(start) == find(end)) {
				System.out.println(n.cost);
				return;
			}
		}
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
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
}