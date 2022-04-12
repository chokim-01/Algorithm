import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static PriorityQueue<Node> pq;

	static boolean[] visit;

	static int ans;

	static class Node implements Comparable<Node> {
		int a, b;

		public Node(int a, int b) {
			// TODO Auto-generated constructor stub
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Node o) {
			if(o.a * this.b == this.a * o.b)
				return -this.a + o.a;
			return o.a * this.b - this.a * o.b;
		}

		@Override
		public String toString() {
			return this.a + " " + this.b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<Node>();
		visit = new boolean[N];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pq.add(new Node(a, b));
		}
		int ans = 0;
		int t = 0;
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			ans += ((n.a * t) % 40000 + n.b) % 40000;
			ans %= 40000;
			t = ans;
		}
		System.out.println(ans);
	}
}
