import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int n, m, t;
	static int s, g, h;
	static long[] v;
	static ArrayList<Link>[] link;
	static HashSet<Integer> candidate;

	static class Link implements Comparable<Link> {
		int d, c;

		public Link(int dest, int cost) {
			// TODO Auto-generated constructor stub
			this.d = dest;
			this.c = cost;
		}

		@Override
		public int compareTo(Main.Link o) {
			// TODO Auto-generated method stub
			return this.c - o.c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input(br);

			v = new long[n + 1];
			Arrays.fill(v, 100000000000L);
			PriorityQueue<Link> q = new PriorityQueue<>();
			q.add(new Link(s, 0));
			v[s] = 0;
			while (!q.isEmpty()) {
				Link now = q.poll();
				for (Link next : link[now.d]) {
					int nextC = now.c + next.c;
					if (v[next.d] <= nextC)
						continue;
					v[next.d] = nextC;
					q.add(new Link(next.d, nextC));
				}
			}
			PriorityQueue<Integer> ans = new PriorityQueue<>();
			for (int can : candidate)
				if ((v[can] & 1) == 1)
					ans.add(can);

			while (!ans.isEmpty())
				sb.append(ans.poll()).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);

	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		candidate = new HashSet<>();
		link = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			link[i] = new ArrayList<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) * 2;
			if ((a == g && b == h) || (a == h && b == g))
				d -= 1;
			link[a].add(new Link(b, d));
			link[b].add(new Link(a, d));
		}
		while (t-- > 0)
			candidate.add(Integer.parseInt(br.readLine()));

	}
}