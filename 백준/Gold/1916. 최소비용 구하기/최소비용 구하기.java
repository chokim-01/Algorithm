import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Link> link[];

	static class Link implements Comparable<Link> {
		int d, c;

		public Link(int d, int c) {
			// TODO Auto-generated constructor stub
			this.d = d;
			this.c = c;
		}

		@Override
		public int compareTo(Main.Link o) {
			// TODO Auto-generated method stub
			return this.c - o.c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		link = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			link[i] = new ArrayList<>();
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			link[a].add(new Link(b, c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[] v = new int[N + 1];
		Arrays.fill(v, Integer.MAX_VALUE);
		v[s] = 0;
		PriorityQueue<Link> q = new PriorityQueue<>();
		q.add(new Link(s, 0));
		while (!q.isEmpty()) {
			Link l = q.poll();
			if (l.d == e) {
				System.out.println(l.c);
				return;
			}
			for (Link nL : link[l.d]) {
				int nLc = l.c + nL.c;
				if (v[nL.d] <= nLc)
					continue;
				v[nL.d] = nLc;
				q.add(new Link(nL.d, nLc));
			}
		}
	}
}