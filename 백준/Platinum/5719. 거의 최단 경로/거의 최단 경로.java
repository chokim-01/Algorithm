import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int start, end;
	static int visit[];
	static Node2 route[];
	static boolean[][] doNotEnter;
	static int minCost;
	static ArrayList<ArrayList<Node>> con;

	static class Node implements Comparable<Node> {
		int dest, cost;

		public Node(int dest, int cost) {
			// TODO Auto-generated constructor stub
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static class Node2 {
		int cost = 0;
		ArrayList<Integer> list = new ArrayList<>();

		public Node2() {
			// TODO Auto-generated constructor stub
			this.cost = 0;
			this.list = new ArrayList<>();
		}

		public void add(int cost, int n) {
			if (this.cost > cost)
				list = new ArrayList<>();
			this.cost = cost;
			list.add(n);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		while (true) {
			if (!input(br))
				break;
			while (true) {
				if (!getCost())
					break;
				delEdge(end);
			}
			sb.append((visit[end] == Integer.MAX_VALUE ? -1 : visit[end]) + "\n");
		}
		System.out.println(sb.toString());
	}

	static void delEdge(int now) {
		Queue<Integer> q = new LinkedList<>();
		q.add(now);
		boolean[] visit = new boolean[N];
		visit[now] = true;
		while (!q.isEmpty()) {
			int n = q.poll();
			for (int next : route[n].list) {
				doNotEnter[next][n] = true;
				if (!visit[next]) {
					visit[next] = true;
					q.add(next);
				}
			}
		}
	}

	static boolean getCost() {
		visit = new int[N];
		route = new Node2[N];
		for (int i = 0; i < N; i++)
			route[i] = new Node2();

		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(start, 0));
		Arrays.fill(visit, Integer.MAX_VALUE);
		visit[start] = 0;

		while (!q.isEmpty()) {
			Node n = q.poll();
			int now = n.dest;

			if (visit[now] < n.cost)
				continue;

			for (Node next : con.get(now)) {
				if (doNotEnter[now][next.dest])
					continue;

				int nCost = visit[now] + next.cost;

				if (nCost < visit[next.dest]) {
					visit[next.dest] = nCost;
					q.add(new Node(next.dest, nCost));
					route[next.dest].add(nCost, now);
				} else if (nCost == visit[next.dest])
					route[next.dest].add(nCost, now);
			}
		}
		if (visit[end] == Integer.MAX_VALUE)
			return false;
		else if (minCost == 0 || minCost == visit[end]) {
			minCost = visit[end];
			return true;
		} else if (minCost != visit[end])
			return false;

		return false;
	}

	static boolean input(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		if (N == 0)
			return false;
		minCost = 0;
		doNotEnter = new boolean[N][N];
		con = new ArrayList<>();
		for (int i = 0; i < N; i++)
			con.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		for (int i = 0, a, b, c; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			con.get(a).add(new Node(b, c));
		}

		return true;
	}
}