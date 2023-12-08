import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static HashSet<String> save;
	static ArrayList<Node>[] link;

	static class Node {
		int dest, cost;

		public Node(int d, int c) {
			// TODO Auto-generated constructor stub
			this.dest = d;
			this.cost = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		link = new ArrayList[N];
		for (int i = 0; i < N; i++)
			link[i] = new ArrayList<>();

		for (int i = 1, a, b, c; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());

			link[a].add(new Node(b, c));
			link[b].add(new Node(a, c));
		}
		StringBuilder ans = new StringBuilder();
		for (int i = 0, a, b; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			Queue<Node> q = new ArrayDeque<>();
			q.add(new Node(a, 0));
			boolean[] v = new boolean[N];
			v[a] = true;
			int ret = 0;
			while (!q.isEmpty()) {
				Node n = q.poll();
				for (Node next : link[n.dest]) {
					if (v[next.dest])
						continue;
					if (next.dest == b) {
						ret = n.cost + next.cost;
						break;
					}
					v[next.dest] = true;
					q.add(new Node(next.dest, n.cost + next.cost));
				}
			}
			ans.append(ret).append("\n");
		}
		System.out.println(ans);
	}
}