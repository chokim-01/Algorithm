import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N; // city
	static int M; // bus
	static ArrayList<int[]> bus[];
	static long[] dist;
	static int ans2;
	static StringBuffer ans3;

	static class Node implements Comparable<Node> {
		int x;
		long cnt;

		public Node(int x, long cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.cnt > o.cnt)
				return 1;
			return -1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new long[N + 1];
		bus = new ArrayList[N + 1];
		ans3 = new StringBuffer();
		ans2 = 1;
		
		for (int i = 0; i < N + 1; i++)
			bus[i] = new ArrayList<int[]>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				bus[a].add(new int[] { b, c });
			}
		}
		Arrays.fill(dist, Long.MAX_VALUE);

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> q = new PriorityQueue<Node>();
		dist[start] = 0;

		q.add(new Node(start, 0));
		int[] parent = new int[N + 1];

		long ans = Long.MAX_VALUE;

		while (!q.isEmpty()) {
			Node n = q.poll();
			if (end == n.x) {
				ans = n.cnt;
				break;
			}

			if (bus[n.x].isEmpty())
				continue;
			for (int[] c : bus[n.x]) {
				if (dist[c[0]] <= n.cnt + c[1])
					continue;
				dist[c[0]] = n.cnt + c[1];
				q.add(new Node(c[0], n.cnt + c[1]));
				// x , 비용, 카운트, 지나온것
				parent[c[0]] = n.x;
			}
		}
		ans3.append(start + " ");
		dfs(parent, end);
		System.out.println(ans + "\n" + ans2 + "\n"+ ans3);

	}
	static void dfs(int[] parent, int x) {
		Stack<Integer> s = new Stack<Integer>();
		while(parent[x] != 0) {
			s.add(x);
			x = parent[x];
			ans2 += 1;
		}
		while(!s.isEmpty())
			ans3.append(s.pop()+ " ");
		
	}

}