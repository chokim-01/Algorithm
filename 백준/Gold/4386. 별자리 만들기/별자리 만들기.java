import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static int N;
	static double[][] map;
	static PriorityQueue<Node> link;

	static class UF {
		int[] parent;

		public UF() {
			// TODO Auto-generated constructor stub
			this.parent = IntStream.range(0, N).map(x -> x).toArray();
		}

		void union(int a, int b) {
			a = find(a);
			b = find(b);
			if (a < b)
				parent[a] = b;
			else
				parent[b] = a;
		}

		int find(int x) {
			if (x == parent[x])
				return x;
			return parent[x] = find(parent[x]);
		}
	}

	static class Node implements Comparable<Node> {
		int x, y;
		double c;

		public Node(int x, int y, double c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return Double.compare(this.c, o.c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		makeLink();

		System.out.println(String.format("%.2f", go()));
	}

	static double go() {
		UF uf = new UF();
		double ans = 0;
		while (!link.isEmpty()) {
			Node n = link.poll();
			if (uf.find(n.x) == uf.find(n.y))
				continue;
			uf.union(n.x, n.y);
			ans += n.c;
		}
		return ans;
	}

	static void makeLink() {
		link = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				link.add(new Node(i, j,
						Math.sqrt(Math.pow(map[i][0] - map[j][0], 2) + Math.pow(map[i][1] - map[j][1], 2))));
			}
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new double[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++)
				map[i][j] = Double.parseDouble(st.nextToken());
		}
	}
}