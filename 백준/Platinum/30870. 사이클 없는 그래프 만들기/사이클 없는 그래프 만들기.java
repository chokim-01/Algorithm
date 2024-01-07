import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	static int N, M, K;
	static int[] start;
	static ArrayList<Node> list;
	static ArrayList<Integer>[] link;
	static Map<Integer, Integer> map;

	static class UF {
		int[] parent;

		public UF() {
			// TODO Auto-generated constructor stub
			this.parent = IntStream.rangeClosed(0, N).map(i -> i).toArray();
		}

		void union(int x, int y) {
			x = find(x);
			y = find(y);
			if (x < y)
				parent[y] = x;
			else
				parent[x] = y;
		}

		int find(int x) {
			if (x == parent[x])
				return x;
			return parent[x] = find(parent[x]);
		}
	}

	static class Node implements Comparable<Node> {
		int x, y, c;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return o.c - this.c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		bfs();
		UF uf = new UF();
		for (int i = 0; i < list.size(); i++)
			list.get(i).c = Math.min(map.get(list.get(i).x), map.get(list.get(i).y));
		Collections.sort(list);
		for (Node n : list) {
			if (uf.find(n.x) == uf.find(n.y)) {
				System.out.println(Math.min(map.get(n.x), map.get(n.y)));
				return;
			}
			uf.union(n.x, n.y);
		}
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] v = new boolean[N + 1];
		int c = 1;
		for (int s : start) {
			v[s] = true;
			q.add(s);
			map.put(s, c);
		}
		while (!q.isEmpty()) {
			int size = q.size();
			c++;
			while (size-- > 0) {
				int now = q.poll();
				for (int next : link[now]) {
					if (v[next])
						continue;
					v[next] = true;
					q.add(next);
					map.put(next, c);
				}
			}
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		list = new ArrayList<>();
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
			list.add(new Node(a, b));
		}
		start = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}