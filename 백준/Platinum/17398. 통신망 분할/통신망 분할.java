import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Main {
	static int N, M, Q;

	static class UF {
		int[] parent;
		int[] count;

		public UF() {
			// TODO Auto-generated constructor stub
			this.parent = IntStream.rangeClosed(0, N).toArray();
			this.count = IntStream.rangeClosed(0, N).map(i -> 1).toArray();
		}

		void union(int x, int y) {
			x = find(x);
			y = find(y);
			if (x < y) {
				parent[y] = x;
				count[x] += count[y];
				count[y] = 0;
			} else {
				parent[x] = y;
				count[y] += count[x];
				count[x] = 0;
			}
		}

		int find(int x) {
			if (x == parent[x])
				return x;
			return parent[x] = find(parent[x]);
		}

		int getCount(int x, int y) {
			return count[x] * count[y];
		}
	}

	static class Node implements Comparable<Node> {
		int x, y, idx;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.idx = -1;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.idx - o.idx;
		}

		@Override
		public String toString() {
			return "[" + this.x + " , " + this.y + " , " + this.idx + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		Node[] list = new Node[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		while (Q-- > 0)
			list[Integer.parseInt(br.readLine()) - 1].idx = Q;
		long ans = 0;
		Arrays.sort(list);
		UF uf = new UF();
		for (Node n : list) {
			int fx = uf.find(n.x);
			int fy = uf.find(n.y);
			if (n.idx != -1)
				if (fx != fy)
					ans += uf.getCount(fx, fy);
			if (fx != fy)
				uf.union(n.x, n.y);
		}
		System.out.println(ans);
	}
}
