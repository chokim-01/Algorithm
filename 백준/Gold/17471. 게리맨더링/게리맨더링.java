import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
		for (int c = 1; c <= N / 2; c++)
			comb(0, c, new boolean[N]);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	static void comb(int idx, int choice, boolean[] visit) {
		if (choice == 0) {
			// execute
			int a = 0;
//			a = graph(visit); // grpah
			a = bfs(visit); // bfs

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

	static int bfs(boolean[] visit) {
		int start1 = 0; // 구역 1, visit을 방문 해야함
		int start2 = 0; // 구역 2, !visit을 방문해야함
		boolean[] newVisit = new boolean[N];
		for (int i = 0; i < N; i++) {
			newVisit[i] = !visit[i];
			if (visit[i])
				start1 = i;
			else
				start2 = i;
		}
		boolean[] v2 = findSection(start2, visit);
		for (int i = 0; i < N; i++)
			if (!visit[i] && !v2[i])
				return Integer.MAX_VALUE;

		boolean[] v1 = findSection(start1, newVisit);
		for (int i = 0; i < N; i++)
			if (!newVisit[i] && !v1[i])
				return Integer.MAX_VALUE;
		
		int ans = 0;
		for (int i = 0; i < N; i++)
			if (visit[i])
				ans += people[i];
		return Math.abs(2 * ans - total);
	}

	static boolean[] findSection(int x, boolean[] v) {
		boolean[] visit = v.clone();
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visit[x] = true;
		while (!q.isEmpty()) {
			int n = q.poll();
			for (int next : con[n]) {
				if (visit[next])
					continue;
				visit[next] = true;
				q.add(next);
			}
		}
		return visit;
	}

	static int graph(boolean[] visit) {
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
				} else if (!visit[i] && !visit[n]) {
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
			while (st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				con[i].add(a);
			}
		}
	}
}