import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Link>[] link;

	static class Link {
		int d, c;

		public Link(int d, int c) {
			// TODO Auto-generated constructor stub
			this.d = d;
			this.c = c;
		}
	}

	static class Node implements Comparable<Node> {
		int x, c, f;
		boolean flag;

		public Node(int x, int c, int f, boolean flag) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.c = c;
			this.f = f;
			this.flag = flag;
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
		StringBuilder sb = new StringBuilder();
		Queue<Node> q;
		int[] v = new int[N + 1];
		int[] ans = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			Arrays.fill(v, Integer.MAX_VALUE);
			Arrays.fill(ans, -1);
			q = new ArrayDeque<>();
			q.add(new Node(n, 0, -1, false));
			ans[n] = v[n] = 0;
			while (!q.isEmpty()) {
				Node now = q.poll();
				for (Link next : link[now.x]) {
					int nextC = now.c + next.c;
					if (v[next.d] <= nextC)
						continue;
					v[next.d] = nextC;
					int f = now.f;
					if (!now.flag)
						f = next.d;

					ans[next.d] = f;
					q.add(new Node(next.d, nextC, f, true));
				}
			}
			for (int i = 1; i <= N; i++)
				sb.append(ans[i] == 0 ? "- " : ans[i] + " ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			link[a].add(new Link(b, c));
			link[b].add(new Link(a, c));
		}

	}
}