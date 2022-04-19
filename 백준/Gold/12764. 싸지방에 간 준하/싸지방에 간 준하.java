import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static class Node implements Comparable<Node> {
		int p, q;

		public Node(int p, int q) {
			// TODO Auto-generated constructor stub
			this.p = p;
			this.q = q;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.p - o.p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			pq.add(new Node(p, q));
		}

		PriorityQueue<Integer> times = new PriorityQueue<>();
		List<int[]> ans = new ArrayList<>();

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			times.add(n.q);

			int index = 0;
			if (times.peek() <= n.p) {
				for (; index < ans.size(); index++) {
					if (n.p >= ans.get(index)[0]) {
						ans.get(index)[0] = n.q;
						times.poll();
						ans.get(index)[1] += 1;
						break;
					}
				}
			}
			// 들어갈 자리가 없음.
			else
				ans.add(new int[] { n.q, 1 });

		}

		StringBuffer sb = new StringBuffer();
		for (int[] a : ans)
			sb.append(a[1] + " ");
		System.out.println(ans.size());
		System.out.println(sb.toString());

	}
}
