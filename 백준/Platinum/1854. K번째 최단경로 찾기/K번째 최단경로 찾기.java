import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static ArrayList<Node>[] link;
	static PriorityQueue<Integer>[] v;

	static class Node implements Comparable<Node> {
		int x, c;

		public Node(int x, int c) {
			// TODO Auto-generated constructor stub
			this.x = x;
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
		input(br);
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(1, 0));
		v[1].add(0);
		while (!q.isEmpty()) {
			Node now = q.poll();
			for (Node next : link[now.x]) {
				int nextC = now.c + next.c;
				if (v[next.x].size() == K && v[next.x].peek() <= nextC)
					continue;
				if (v[next.x].size() == K && v[next.x].peek() > nextC)
					v[next.x].poll();
				v[next.x].add(nextC);
				q.add(new Node(next.x, nextC));
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1, k; i <= N; i++) {
			k = K;
			if (v[i].size() < K)
				sb.append("-1");
			else
				sb.append(v[i].poll());
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			link[a].add(new Node(b, c));
		}
		v = new PriorityQueue[N + 1];
		for (int i = 1; i <= N; i++)
			v[i] = new PriorityQueue<>(Collections.reverseOrder());
	}
}
