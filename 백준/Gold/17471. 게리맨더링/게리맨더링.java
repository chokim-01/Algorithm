import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int total, answer;
	static int[] people;
	static int[] parent, parent2;
	static HashSet<Integer>[] con;

	public static void main(String[] args) throws IOException {
		input();
		answer = Integer.MAX_VALUE;
		// graph
		graph();
		// bfs
//		bfs();

		if (answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	static class Node {
		int x;
		boolean[] v = new boolean[N];

		public Node(int x) {
			// TODO Auto-generated constructor stub
			this.x = x;
		}
	}

	static void bfs() {
		for (int t = 0; t < N; t++) { // 정점 1개 선택
			Queue<Node> q = new LinkedList<>();
			Node n = new Node(t);
			n.v[t] = true;
			q.add(new Node(t));
			
			while (!q.isEmpty()) {
				n = q.poll();
				// q2 선택하지 않은 곳 아무데서나.
				int b = 0;
				for (; b < N; b++)
					if (!n.v[b])
						break;
				if (b == N)
					return;
				if (bfs2(b, n.v)) {
					int area1 = 0;
					for (int i = 0; i < N; i++)
						if (n.v[i])
							area1 += people[i];
					int a12 = Math.abs(2 * area1 - total);
					answer = answer < a12 ? answer : a12;
				}
				for (int c : con[n.x]) {
					if (n.v[c])
						continue;
					Node nc = new Node(c);
					nc.v = n.v.clone();
					nc.v[c] = true;
					q.add(nc);
				}
			}
		}
	}

	static boolean bfs2(int x, boolean[] visit2) {
		boolean[] visit = visit2.clone();
		Queue<Integer> q = new LinkedList<>();
		visit[x] = true;
		q.add(x);
		while (!q.isEmpty()) {
			int n = q.poll();
			for (int c : con[n]) {
				if (visit[c])
					continue;
				visit[c] = true;
				q.add(c);
			}
		}
		for (int i = 0; i < N; i++)
			if (!visit[i])
				return false;
		return true;
	}

	static void graph() {
		for (int c = 1; c <= N / 2; c++)
			comb(0, c, new boolean[N]);
	}

	static void comb(int idx, int choice, boolean[] visit) {
		if (choice == 0) {
			// execute
			int a = findParent(visit);
			answer = answer < a ? answer : a;
			return;
		}
		if (idx == N)
			return;
		// choice
		visit[idx] = true;
		comb(idx + 1, choice - 1, visit);
		visit[idx] = false;
		comb(idx + 1, choice, visit);
	}

	static int findParent(boolean[] visit) {
		parent = new int[N];
		parent2 = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			parent2[i] = i;
		}
		for (int i = 0; i < N; i++) {
			for (int n : con[i]) {
				if (visit[i] && visit[n]) {
					union(i, n, parent);
				} else if(!visit[i] && !visit[n]){
					union(i, n, parent2);
				}
			}
		}
		ArrayList<Integer> a1 = new ArrayList<>();
		ArrayList<Integer> a2 = new ArrayList<>();
		HashSet<Integer> p1 = new HashSet<>();
		HashSet<Integer> p2 = new HashSet<>();
		int area1 = 0;
		for (int i = 0; i < N; i++) {
			if (visit[i]) {
				a1.add(i);
				p1.add(find(i, parent));
				area1 += people[i];
			} else {
				a2.add(i);
				p2.add(find(i, parent2));
			}
		}
		if (p1.size() != 1 || p2.size() != 1)
			return Integer.MAX_VALUE;
		return Math.abs(2 * area1 - total);
	}

	static int find(int x, int[] p) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x], p);
	}

	static void union(int x, int y, int[] p) {
		x = find(x, p);
		y = find(y, p);
		if (x == y)
			return;
		if (x < y)
			p[y] = x;
		else
			p[x] = y;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		con = new HashSet[N];
		total = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0, c; i < N; i++) {
			c = Integer.parseInt(st.nextToken());
			total += c;
			people[i] = c;
			con[i] = new HashSet<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("0"))
				continue;
			while (st.hasMoreTokens())
				con[i].add(Integer.parseInt(st.nextToken()) - 1);
		}
	}
}